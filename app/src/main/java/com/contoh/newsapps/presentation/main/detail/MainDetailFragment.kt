package com.contoh.newsapps.presentation.main.detail

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.contoh.newsapps.R
import com.contoh.newsapps.databinding.MainFragmentDetailBinding
import com.contoh.newsapps.presentation.base.BaseFragment
import com.contoh.newsapps.utils.ext.loadImageUrl
import com.contoh.newsapps.utils.widget.viewBinding

class MainDetailFragment : BaseFragment(R.layout.main_fragment_detail) {

    private val binding: MainFragmentDetailBinding by viewBinding()
    private val viewModel: MainDetailViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()
    }

    private fun setupLayout() {
        with(binding) {
            withState(viewModel) { state ->
                val data = state.news
                mainIvItemNews.loadImageUrl(data.urlToImage)
                mainTvItemNewsAuthor.text = data.author
                mainTvItemNewsTitle.text = data.title
                mainTvItemNewsContent.text = data.content
            }
        }
    }

    override fun invalidate() = Unit
}
