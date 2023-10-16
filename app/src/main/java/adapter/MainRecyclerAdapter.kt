package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapplication.R
import model.User

class MainRecyclerAdapter (
    private val list: List<User>,
    private val onClick: (id: Int) -> Unit
    ): RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>()
    {
        inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView) {
            val textViewName =
                itemView.findViewById<TextView>(R.id.textViewName)
            val textViewEmail =
                itemView.findViewById<TextView>(R.id.textViewEmail)
            init {
                itemView.setOnClickListener{
                    onClick(list[adapterPosition].id)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {

            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.textViewName.text = list[position].name
            holder.textViewEmail.text = list[position].email
        }
    }
