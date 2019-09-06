package com.outerspace.kgithubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_item.view.*

class MainActivity : AppCompatActivity() {

    private val adapter: MainAdapter = MainAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        adapter.addUser(User("Luis", "http://users/luis.jpg", 52))
        adapter.addUser(User("Elvi", "http://users/elvi.jpg", 70))
        adapter.addUser(User("Aldo", "http://users/aldo.jpg", 80))
        adapter.addUser(User("Sofi", "http://users/sofi.jpg", 95))
    }
}

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val userList : MutableList<User> = mutableListOf()

    fun setUserList(userList : List<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    fun addUser(user : User) {
        userList.add(user)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MainViewHolder(view);
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        val user : User = userList.get(position)
        holder.setUser(user)
    }

    class MainViewHolder(viewHolder : View) : RecyclerView.ViewHolder(viewHolder) {
        fun setUser(user : User) {
            itemView.userName.text = user.name;
            itemView.repoCount.text = user.repoCount.toString()
        }
    }
}

class User(name : String, avatarUrl : String, repoCount :Int) {
    lateinit var name: String
    lateinit var avatarUrl: String
    var repoCount: Int = 0

    init {
        this.name = name
        this.avatarUrl = avatarUrl
        this.repoCount = repoCount
    }
}