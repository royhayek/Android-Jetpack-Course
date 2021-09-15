
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpackcourse.BR
import com.example.androidjetpackcourse.data.model.GitRepo
import com.example.androidjetpackcourse.databinding.GitRepoListItemBinding

class GitRepoAdapter : PagingDataAdapter<GitRepo, GitRepoAdapter.RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoAdapter.RepoViewHolder {
        val binding = GitRepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitRepoAdapter.RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.gitRepoListItemBinding.setVariable(BR.gitRepo, repo)

    }

   inner class RepoViewHolder(val gitRepoListItemBinding: GitRepoListItemBinding) : RecyclerView.ViewHolder(gitRepoListItemBinding.root)

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldGitRepo: GitRepo, newGitRepo: GitRepo): Boolean =
                oldGitRepo.fullName == newGitRepo.fullName

            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}