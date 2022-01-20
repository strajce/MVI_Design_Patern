package com.example.mvi_design_patern.ui

import androidx.lifecycle.*
import com.example.mvi_design_patern.model.Blog
import com.example.mvi_design_patern.repository.MainRepository
import com.example.mvi_design_patern.ui.MainStateEvent.GetBlogEvent
import com.example.mvi_design_patern.ui.MainStateEvent.None
import com.example.mvi_design_patern.util.DataState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is GetBlogEvent -> {
                    mainRepository.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is None -> {
                    //Nothing
                }
            }
        }
    }
}

sealed class MainStateEvent {

    object GetBlogEvent : MainStateEvent()
    object None : MainStateEvent()
}