package com.example.mnt_android.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mnt_android.extension.checkUploadDate
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.util.FALSE_INT
import com.example.mnt_android.util.getFruttoData
import com.example.mnt_android.view.adapter.*
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
            isManager = _isManager
            setList(list)
        }
    }
}

@BindingAdapter("adapterManitoList", "isManager")
fun bindAdapterManitoList(
    view: RecyclerView,
    memberList: ArrayList<Applicant>?,
    isManager: Boolean = false
) {
    memberList?.let { list ->
        (view.adapter as ManitoListAdapter).run {
            this.isManager = isManager
            setList(list)
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

@BindingAdapter("adapterMissionList", "isManager")
fun bindAdapterMissionList(
    view: RecyclerView,
    missionList: ArrayList<UserMissionResponse>?,
    _isManager: Boolean = false
) {
    missionList?.let { list ->
        (view.adapter as ContentListAdapter).run {
            isManager = _isManager
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
            setList(list)
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

@BindingAdapter("fruitProfileSrc")
fun setFruitProfileSrc(view: ImageView, id: Int?) {
    val context = view.context
    val imgNm = getFruttoData(context, (id ?: -1) + 1)?.englishName
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
    val imgNm = getFruttoData(context, (id ?: -1) + 1)?.englishName
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
    val imgNm = getFruttoData(context, (id ?: -1) + 1)?.backgroundFruit1
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
    val imgNm = getFruttoData(context, (id ?: -1) + 1)?.backgroundFruit2
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
    val imgNm = getFruttoData(context, (id ?: -1) + 1)?.backgroundFruit2
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

@BindingAdapter("nickName", "isManager")
fun setNickName(view: TextView, name: String?, isManager: Boolean) {
    name?.let {
        if (isManager) {
            view.text = it
        } else {
            val context = view.context
            view.text = getFruttoData(context, it.toInt()+1)?.koreanNickName
        }
    }
}

@BindingAdapter("endDayToDDay")
fun convertEndDayToDDay(view: TextView, endDay: String?) {
    endDay?.let {
        val ONE_DAY = 24 * 60 * 60 * 1000

        val endDayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val calendar = Calendar.getInstance()
        calendar.time = endDayFormat.parse(it)
        val dday = (calendar.timeInMillis - System.currentTimeMillis()) / ONE_DAY
        view.text = if (dday > 0) dday.toString() else "0"
    }
}