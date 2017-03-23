package moe.linux.boilerplate.viewModel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import moe.linux.boilerplate.BR
import moe.linux.boilerplate.databinding.FragmentFrontBinding
import timber.log.Timber
import javax.inject.Inject

class BoardViewModel @Inject constructor(val db: DatabaseReference) : BaseObservable() {

    @Bindable
    var newPostText = ""
    var enableTextBox = true

    val messageList = ObservableArrayList<Post>()

    lateinit var binding: FragmentFrontBinding

    fun start() {
        db.child("message").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {
                p0?.children?.forEach {
                    Timber.d("list: ${it.value}")
                }
                Timber.d("add message : ${p0?.value.toString()}")
            }

            override fun onCancelled(p0: DatabaseError?) {
            }
        })
    }

    fun sendText(v: View) {
        Timber.d("click")
        if (newPostText.isNullOrEmpty()) {
            Toast.makeText(binding.root.context, "please input the text", Toast.LENGTH_LONG).show()
        } else {
            enableTextBox = false
            db.child("message").push().setValue(Post(text = newPostText))
                .addOnCompleteListener {
                    enableTextBox = true
                    Toast.makeText(binding.root.context, "save message", Toast.LENGTH_LONG).show()
                    newPostText = ""
                    notifyPropertyChanged(BR.newPostText)
                }
        }
    }
}

data class Post(
    var text: String = "",
    var user: Int = 0,
    var star: Map<Int, Boolean> = emptyMap(),
////.;
    @Exclude
    var uid: String = ""
)
