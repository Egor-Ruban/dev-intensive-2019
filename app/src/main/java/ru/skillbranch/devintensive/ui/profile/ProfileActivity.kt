package ru.skillbranch.devintensive.ui.profile

import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    var isEditMode = false
    var isValidRepo = true
    private lateinit var viewFields: Map<String, TextView>
    private lateinit var viewModel : ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        et_repository.doOnTextChanged { text, start, count, after ->
            validateRepo()
        }
        initViews(savedInstanceState)
        initViewModel()
    }

    private fun initViews(savedInstanceState: Bundle?) {
        viewFields = mapOf(
            "nickName" to tv_nick_name,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository,
            "rating" to tv_rating,
            "respect" to tv_respect)

        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE) ?: false
        showCurrentMode(isEditMode)

        isValidRepo = savedInstanceState?.getBoolean("IS_VALID_REPO") ?: true

        btn_edit.setOnClickListener {
            if(isEditMode) saveProfileInfo()
            isEditMode = isEditMode.not()
            showCurrentMode(isEditMode)
        }

        btn_switch_theme.setOnClickListener{
            viewModel.switchTheme()
        }


    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it)})
        viewModel.getTheme().observe(this, Observer { updateTheme(it) })
    }

    private fun updateUI(profile: Profile) {
        profile.toMap().also {
            for ((k, v) in viewFields) {
                v.text = it[k].toString()
            }
        }
    }

    private fun updateTheme(mode : Int){
        delegate.setLocalNightMode(mode)
    }

    private fun saveProfileInfo(){
        if(!validateRepo()) et_repository.setText("")
        wr_repository.isErrorEnabled = false
        isValidRepo = true
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply{
            viewModel.saveProfileData(this)
        }
    }

    fun validateRepo() : Boolean{
        val ex = resources.getStringArray(R.array.exclude)
            var url = et_repository.text.toString()

            url = url.replace("http://", "")
            url = url.replace("https://", "")
            url = url.replace("www.", "")

            val uri = Uri.parse(url)
            var isValid : Boolean
            val segments = uri.pathSegments

        isValid = !(segments.size != 2 ||
                ex.contains(segments[1]) ||
                segments[0] != "github.com" ||
                url.contains("//")||
                segments[1].contains(" ") ||
                segments[1].contains("--") ||
                segments[1].contains("+") ||
                segments[1].contains("=")||
                segments[1].contains("!")||
                segments[1].contains("_"))
            if(url == "") isValid = true
            //Log.d("M_Main", "\t\t\t$isValid\n ")

            if(!isValid){
                wr_repository.error = "Невалидный адрес репозитория"
            } else {
                wr_repository.isErrorEnabled = false
            }
        isValidRepo = isValid
        return isValid
    }

    private fun showCurrentMode(isEdit: Boolean) {
        val info = viewFields.filter {
            setOf("firstName", "lastName", "about", "repository" ).contains(it.key)
        }

        info.forEach {
            val v = it.value as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if (isEdit) 255 else 0
        }

        ic_eye.visibility = if (isEdit) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEdit

        with(btn_edit){
            val filter: ColorFilter? = if (isEdit){
                PorterDuffColorFilter(resources.getColor(R.color.color_accent,theme), PorterDuff.Mode.SRC_IN)
            }
            else null

            val icon =
                if (isEdit)
                    resources.getDrawable(R.drawable.ic_save_black_24dp,theme)
                else resources.getDrawable(R.drawable.ic_edit_black_24dp,theme)

            background.colorFilter = filter
            setImageDrawable(icon)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_EDIT_MODE,isEditMode)
        outState.putBoolean("IS_VALID_REPO", isValidRepo)
    }
}