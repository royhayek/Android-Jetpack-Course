package com.example.androidjetpackcourse.data.network
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.androidjetpackcourse.data.model.GitRepo
import java.io.IOException

class GitRepoPagingSource(private val service: GitRepoService) : PagingSource<Int, GitRepo>() {

    override fun getRefreshKey(state: PagingState<Int, GitRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitRepo> {
        val page = params.key ?: FIRST_PAGE

        return try {
            val response = service.getRepositories(page, PAGE_SIZE, TOPIC)
            val repos = response.items

            LoadResult.Page(
                data = repos!!,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (repos.isEmpty()) null else page + 1
            )
        }  catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
        const val TOPIC = "android"
    }

}