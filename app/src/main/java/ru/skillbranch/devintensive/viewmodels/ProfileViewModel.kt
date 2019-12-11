package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel() {
    private val repository : PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()

    init{
        profileData.value = repository.getProfile()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getProfileData() : LiveData<Profile> = profileData

    fun saveProfileData(profile:Profile){
        repository.saveProfile(profile)
        profileData.value = profile
    }
}