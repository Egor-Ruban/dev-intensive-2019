package ru.skillbranch.devintensive.ui.profile


import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel


class ProfileActivity : AppCompatActivity() {
    companion object{
    val IS_EDIT_MODE = "IS_EDIT_MODE"
    }
    var isEdit = false
    lateinit var viewFields : Map<String,TextView>
    lateinit var viewModel : ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        initViews(savedInstanceState)
        initViewModel()
    }

    private fun showCurrentMode(){
        val info = viewFields.filter { setOf("first_name" ,"last_name" ,"about" ,"repository").contains(it.key)}
        for((_,V) in info){
            V as EditText
            V.isFocusable = isEdit
            V.isFocusableInTouchMode = isEdit
            V.isEnabled = isEdit
            V.background.alpha = if(isEdit) 255 else 0
        }

        ic_eye.visibility = if(isEdit) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEdit

        with(btn_edit){
            val filter : ColorFilter? = if(isEdit){
                PorterDuffColorFilter(resources.getColor(R.color.color_accent,theme), PorterDuff.Mode.SRC_IN)
            } else {
                null
            }

            val icon =  if(isEdit) {
                resources.getDrawable(R.drawable.ic_save_black_24dp, theme)
            } else {
                resources.getDrawable(R.drawable.ic_edit_black_24dp, theme)
            }

            background.colorFilter = filter
            setImageDrawable(icon)
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it)})
        viewModel.getTheme().observe(this, Observer { updateTheme(it) })
    }

    private fun updateTheme(mode: Int){
        delegate.setLocalNightMode(mode)
    }

    private fun updateUI(profile:Profile){
        profile.toMap().also {
            for((K,V) in viewFields){
                V.text = it[K].toString()
            }
        }
    }

    private fun saveProfileInfo(){
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply {
            viewModel.saveProfileData(this)
        }


    }
    private fun initViews(savedInstanceState: Bundle?){
        isEdit = savedInstanceState?.getBoolean(IS_EDIT_MODE) ?: false
        viewFields = mapOf(
            "nickname" to tv_nick_name,
            "rank" to tv_rank,
            "rating" to tv_rating,
            "respect" to tv_respect,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository
        )

        btn_switch_theme.setOnClickListener{
            viewModel.switchTheme()
        }
        btn_edit.setOnClickListener{
            if(isEdit) saveProfileInfo()
            isEdit=!isEdit
            showCurrentMode()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean(IS_EDIT_MODE,isEdit)
    }
}