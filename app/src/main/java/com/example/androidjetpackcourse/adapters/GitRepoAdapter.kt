
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpackcourse.BR
import com.example.androidjetpackcourse.data.model.GitRepo
import com.example.androidjetpackcourse.databinding.GitRepoListItemBinding

class GitRepoAdapter : PagingDataAdapter<GitRepo, GitRepoAdapter.RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = GitRepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.apply {
            if (repo != null) {
                bind(repo)
            }
        }
    }

    class RepoViewHolder(val binding: GitRepoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: GitRepo) {
            binding.apply {
                gitRepo = repo
            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldGitRepo: GitRepo, newGitRepo: GitRepo): Boolean =
                oldGitRepo.full_name == newGitRepo.full_name

            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem == newItem
            }
        }
    }
}