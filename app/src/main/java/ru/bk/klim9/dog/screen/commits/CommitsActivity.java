package ru.bk.klim9.dog.screen.commits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.bk.klim9.dog.R;
import ru.bk.klim9.dog.content.CommitResponse;
import ru.bk.klim9.dog.content.Repository;
import ru.bk.klim9.dog.screen.general.LoadingDialog;
import ru.bk.klim9.dog.screen.general.LoadingView;
import ru.bk.klim9.dog.utils.PreferenceUtils;
import ru.bk.klim9.dog.widget.DividerItemDecoration;
import ru.bk.klim9.dog.widget.EmptyRecyclerView;
import rx.Observable;

/**
 * @author Ivan
 */
public class CommitsActivity extends AppCompatActivity implements CommitView{

    private static final String REPO_NAME_KEY = "repo_name_key";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;

    private CommitAdapter mAdapter;

    private CommitPresenter mPresenter;

    public static void start(@NonNull Activity activity, @NonNull Repository repository) {
        Intent intent = new Intent(activity, CommitsActivity.class);
        intent.putExtra(REPO_NAME_KEY, repository.getName());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        String repositoryName = getIntent().getStringExtra(REPO_NAME_KEY);
        String user = PreferenceUtils.getUser();

        mAdapter = new CommitAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new CommitPresenter(lifecycleHandler, this);
        mPresenter.init(user, repositoryName);

    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showCommits(@NonNull List<CommitResponse> commitResponses) {
        mAdapter.changeDataSet(commitResponses);
    }

    @Override
    public void showError() {
        Snackbar.make(mRecyclerView, "Ошибка соединения, которую мы можеи обработать", Snackbar.LENGTH_LONG).show();
    }
}
