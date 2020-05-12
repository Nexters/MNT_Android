package com.nexters.frutto.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.frutto.extension.isTrue
import com.nexters.frutto.service.model.Applicant
import com.nexters.frutto.service.model.UserMissionResponse
import com.nexters.frutto.service.repository.PreferencesRepository
import com.nexters.frutto.util.FALSE_INT
import com.nexters.frutto.util.getFruttoData
import com.nexters.frutto.view.adapter.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@BindingAdapter("adapterApplicantList", "isManager")
fun bindAdapterApplicantList(
    view: RecyclerView,
    applicantList: ArrayList<Applicant>?,
    _isManager: Int = FALSE_INT
) {
    applicantList?.let { list ->
        (view.adapter as ApplicantListAdapter).run {
            val userList = arrayListOf<Applicant>()
            list.forEach { applicant ->
                if(applicant.isCreater != -1) {
                    userList.add(applicant)
                }
            }
            isManager = _isManager
            setList(userList)
        }
    }
}

@BindingAdapter("adapterManitoList", "isPublic")
fun bindAdapterManitoList(
    view: RecyclerView,
    memberList: ArrayList<Applicant>?,
    isPublic: Boolean = false
) {
    memberList?.let { list ->
        (view.adapter as ManitoListAdapter).run {
            val userList = arrayListOf<Applicant>()
            list.forEach { applicant ->
                if (!applicant.isCreater.isTrue)
                    userList.add(applicant)
            }
            this.isPublic = isPublic
            setList(userList.apply {
                sortBy { it.manitto.name }
            })
        }
    }
}

@BindingAdapter("adapterMissionManagerList")
fun bindAdapterMissionManagerList(
    view: RecyclerView,
    missionList: ArrayList<Pair<String,String>>?
) {
    missionList?.let { list ->
        (view.adapter as MissionManagerListAdapter).run {
            setList(list)
        }
    }
}

@BindingAdapter("adapterNotDoneMissionTypeList")
fun bindAdapterNotDoneMissionTypeList(
    view: RecyclerView,
    missionTypeList: ArrayList<UserMissionResponse>?
) {
    missionTypeList?.let { list ->
        (view.adapter as NotDoneMissionListAdapter).run {
            setList(list)
        }
    }
}

@BindingAdapter("adapterDoneMissionTypeList")
fun bindAdapterDoneMissionTypeList(
    view: RecyclerView,
    missionTypeList: ArrayList<UserMissionResponse>?
) {
    missionTypeList?.let { list ->
        (view.adapter as DoneMissionListAdapter).run {
            setList(list)
        }
    }
}

@BindingAdapter("adapterMissionList", "isPublic")
fun bindAdapterMissionList(
    view: RecyclerView,
    missionList: ArrayList<UserMissionResponse>?,
    isPublic: Boolean = false
) {
    missionList?.let { list ->
        (view.adapter as ContentListAdapter).run {
            this.isPublic = isPublic
            setList(list)
        }
    }
}

@BindingAdapter("adapterSelectApplicantList")
fun bindSelectApplicantList(
    view: RecyclerView,
    applicantList: ArrayList<Applicant>?
) {
    applicantList?.let { list ->
        (view.adapter as SelectManitoListAdapter).run {
            val userId = PreferencesRepository(view.context).getUserId()
            val userList = arrayListOf<Applicant>()
            list.forEach { applicant ->
                if (!applicant.isCreater.isTrue && applicant.user.id != userId)
                    userList.add(applicant)
            }
            setList(userList.apply {
                sortBy { it.user.name }
            })
        }
    }
}

@BindingAdapter("android:src")
fun setIvSrc(view: ImageView, imgSrc: String?) {
    imgSrc?.let {
        Glide.with(view.context)
            .load(it)
            .into(view)
    } ?: { view.visibility = View.GONE }()
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = when(isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

@BindingAdapter("fruitProfileSrc")
fun setFruitProfileSrc(view: ImageView, id: Int?) {
    val context = view.context
    val imgNm = getFruttoData(context, id ?: 0)?.englishName
    val imgRes = context.resources.getIdentifier(
        "img_profile_${imgNm}",
        "drawable",
        context.packageName
    )
    view.setImageResource(imgRes)
}

@BindingAdapter("fruitChatProfileSrc")
fun setFruitChatProfileSrc(view: ImageView, id: Int?) {
    val context = view.context
    val imgNm = getFruttoData(context, id ?: 0)?.englishName
    val imgRes = context.resources.getIdentifier(
        "img_profile_chat_${imgNm}",
        "drawable",
        context.packageName
    )
    view.setImageResource(imgRes)
}

@BindingAdapter("fruitIconProfileSrc1")
fun setFruitIconProfileSrc1(view: ImageView, id: Int?) {
    val context = view.context
    val imgNm = getFruttoData(context, id ?: 0)?.backgroundFruit1
    val imgRes = context.resources.getIdentifier(
        "img_profile_icon_${imgNm}",
        "drawable",
        context.packageName
    )
    view.setImageResource(imgRes)
}

@BindingAdapter("fruitIconProfileSrc2")
fun setFruitIconProfileSrc2(view: ImageView, id: Int?) {
    val context = view.context
    val imgNm = getFruttoData(context, id ?: 0)?.backgroundFruit2
    val imgRes = context.resources.getIdentifier(
        "img_profile_icon_${imgNm}",
        "drawable",
        context.packageName
    )
    view.setImageResource(imgRes)
}

@BindingAdapter("popupEndFruit")
fun setPopupEndFruit(view: ImageView, id: Int?) {
    val context = view.context
    val imgNm = getFruttoData(context, id ?: 0)?.englishName
    val imgRes = context.resources.getIdentifier(
        "img_popup_end_${imgNm}",
        "drawable",
        context.packageName
    )
    view.setImageResource(imgRes)
}

@BindingAdapter("faceProfileSrc")
fun setFaceProfileSrc(view: ImageView, id: Int?) {
    id?.let {
        val context = view.context
        val imgRes = context.resources.getIdentifier(
            "img_profile_face_${"%02d".format(it)}",
            "drawable",
            context.packageName
        )
        view.setImageResource(imgRes)
    }
}

@BindingAdapter("autoNickName", "isPublic")
fun setAutoNickName(view: TextView, name: String?, isPublic: Boolean) {
    name?.let {
        if (isPublic) {
            view.text = it
        } else {
            setNickName(view, it.toInt())
        }
    }
}

@BindingAdapter("nickName")
fun setNickName(view: TextView, fruttoId: Int?) {
    fruttoId?.let {
        val context = view.context
        view.text = getFruttoData(context, it)?.koreanNickName
    }
}

@BindingAdapter("endDayToDDay")
fun convertEndDayToDDay(view: TextView, endDayStr: String?) {
    endDayStr?.let {
        val ONE_DAY = 24 * 60 * 60 * 1000

        val endDayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val calendar = Calendar.getInstance()
        calendar.time = endDayFormat.parse(it)
        val nowDay: Long = System.currentTimeMillis() / ONE_DAY
        val endDay: Long = calendar.timeInMillis / ONE_DAY
        val dday = endDay - nowDay
        view.text = if (dday > 0) dday.toString() else "0"
    }
}