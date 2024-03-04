package com.contoh.newsapps.presentation.main.topheadline

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.contoh.newsapps.R
import com.contoh.newsapps.databinding.MainFragmentTopHeadlineBinding
import com.contoh.newsapps.databinding.MainItemNewsBinding
import com.contoh.newsapps.domain.models.news.NewsDto
import com.contoh.newsapps.presentation.base.BaseFragment
import com.contoh.newsapps.presentation.main.detail.MainDetailArgs
import com.contoh.newsapps.presentation.main.everything.MainEverythingFragmentDirections
import com.contoh.newsapps.utils.ext.loadImageUrl
import com.contoh.newsapps.utils.helper.DateTimeHelper
import com.contoh.newsapps.utils.widget.CommonRecyclerViewAdapter
import com.contoh.newsapps.utils.widget.viewBinding

class MainTopHeadlineFragment : BaseFragment(R.layout.main_fragment_top_headline) {

    private val binding: MainFragmentTopHeadlineBinding by viewBinding()
    private val viewModel: MainTopHeadlineViewModel by fragmentViewModel()

    private val newsAdapter = CommonRecyclerViewAdapter(
        itemLayoutRes = R.layout.main_item_news,
        onBind = this::onBinNewsItem,
        onClickListener = this::onNewsItemClickListener
    )

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetState()
        setupLayout()

        subscribeToNewsAsync()
        subscribeToNews()

        viewModel.getNewsList()
    }

    private fun setupLayout() {
        with(binding) {
            mainRvNews.layoutManager = linearLayoutManager
            mainRvNews.adapter = newsAdapter
        }
    }

    private fun resetState() {
        viewModel.reset()
    }

    override fun invalidate() = Unit

    override fun onDestroyView() {
        binding.mainRvNews.layoutManager = null

        super.onDestroyView()
    }

    @SuppressLint("SetTextI18n")
    private fun onBinNewsItem(
        data: NewsDto,
        view: View
    ) = MainItemNewsBinding.bind(view).apply {
        withState(viewModel) {
            mainTvItemNewsDate.text = DateTimeHelper.convertTimeStr(
                data.publishedAt, DateTimeHelper.API_TZ_FORMAT_SIMPLER, DateTimeHelper.DAY_ONLY
            )
            mainTvItemNewsMonthYear.text = DateTimeHelper.convertTimeStr(
                data.publishedAt, DateTimeHelper.API_TZ_FORMAT_SIMPLER, DateTimeHelper.MONTH_YEAR_ONLY
            )

            mainIvItemNews.loadImageUrl(data.urlToImage)
            mainTvItemNewsAuthor.text = data.author
            mainTvItemNewsTitle.text = data.title

        }
    }

    private fun onNewsItemClickListener(
        news: NewsDto, position: Int
    ) {
        val direction = MainEverythingFragmentDirections
            .actionMainHomeFragmentToMainFragmentDetail(
                MainDetailArgs(
                    news = news
                )
            )

        findNavController().navigate(direction)
    }

    private fun subscribeToNewsAsync() {
        viewModel.onEach(
            MainTopHeadlineState::newsListAsync,
            uniqueOnly()
        ) {
            when (it) {
                Uninitialized, is Loading -> {
                    showFullScreenLoading(true)

                    viewModel.reset()
                }

                is Success -> {
                    showFullScreenLoading(false)

                    val news = it.invoke()
                    viewModel.updateList(news, 10)
                }

                is Fail -> {
                    showFullScreenLoading(false)

                    viewModel.reset()
                }

                else -> Unit
            }
        }
    }

    private fun subscribeToNews() {
        viewModel.onEach(
            MainTopHeadlineState::newsList,
            uniqueOnly()
        ) { data ->
            val list = data
                .sortedByDescending { "${DateTimeHelper.convertTimeStr(
                    it.publishedAt, DateTimeHelper.DISPLAY_FULL_DATE_FORMAT, DateTimeHelper.MONT_ONLY
                )}${DateTimeHelper.convertTimeStr(
                    it.publishedAt, DateTimeHelper.DISPLAY_FULL_DATE_FORMAT, DateTimeHelper.DAY_ONLY
                )}" }

            newsAdapter.setData(list)
        }
    }
}
