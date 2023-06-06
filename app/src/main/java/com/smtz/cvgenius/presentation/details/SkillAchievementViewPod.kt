package com.smtz.cvgenius.presentation.details

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewPodSkillAchievementBinding
import com.smtz.cvgenius.domain.model.*
import com.smtz.cvgenius.utils.achievement
import com.smtz.cvgenius.utils.skill

class SkillAchievementViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

//    var binding = ViewPodEducationDetailBinding.inflate(LayoutInflater.from(context), this)

    private lateinit var binding: ViewPodSkillAchievementBinding
    private lateinit var mDelegate: ButtonSaveSkillAchievementDelegate

    private var skillOrAchievement = ""

    var selectedStartDate = ""
    var selectedEndDate = ""
    private var title = ""

    private var mSkillVO: SkillsVO? = null
    private var mAchievementVO: AchievementVO? = null
    private var mId: Long = System.currentTimeMillis()

    override fun onFinishInflate() {

        binding = ViewPodSkillAchievementBinding.bind(this)
        setUpListeners()
        super.onFinishInflate()
    }

    //Function to call from Activity
    fun setUpSkillAchiObjSignViewPod(
        delegate: ButtonSaveSkillAchievementDelegate,
        changeBtnAdd: Boolean,
        skillAchiObjSign: String
    ) {
        if (changeBtnAdd) {
            binding.btnDelete.setImageResource(R.drawable.ic_add)
            binding.btnDelete.isClickable = false                  // make btnDelete unclickable
        }
        setDelegate(delegate)

        this.skillOrAchievement = skillAchiObjSign  // setUpViewPod title
        skillOrAchievement()
    }

    //Function to call from ViewHolder
    fun setUpSkillData(skillVO: SkillsVO) {
        mSkillVO = skillVO
        bindData()
    }
    fun setUpAchievementData(achievementVO: AchievementVO) {
        mAchievementVO = achievementVO
        bindData()
    }

    private fun setDelegate(delegate: ButtonSaveSkillAchievementDelegate) {
        this.mDelegate = delegate
    }

    private fun bindData() {
        mSkillVO?.let{
            mId = it.id
            binding.tvDetailNameCollapsed.text = it.skill
            binding.etTitle.setText(it.skill)
        }
        mAchievementVO?.let{
            mId = it.id
            binding.tvDetailNameCollapsed.text = it.achievement
            binding.etTitle.setText(it.achievement)
        }
    }

    private fun skillOrAchievement() {
        when (skillOrAchievement) {
            skill -> {
                setUpSkillAchiText(R.string.lbl_skills)
                binding.tvDetailNameExpanded.text = context.getString(R.string.lbl_skills)
                binding.etTitle.hint = "Ex: Excel"
                binding.etTitle.setLines(1)
            }
            achievement -> {
                setUpSkillAchiText(R.string.lbl_achievements)
            }
        }
    }

    private fun setUpListeners() {
        binding.containerCollapsed.setOnClickListener {
            expandCardView()
        }
        binding.tvDetailNameExpanded.setOnClickListener {
            collapseCardView()
        }

        setUpError(binding.etTitle, binding.error)

        binding.btnSave.setOnClickListener {
            title = binding.etTitle.text.toString().trim()

            if (title.isNotEmpty()) {
                when (skillOrAchievement) {
                    skill ->{
                        mDelegate.onTapSave(SkillsVO(id = mId, skill = title,), null)
                        clearDataAfterSaved()
                    }
                    achievement -> {
                        mDelegate.onTapSave(null, AchievementVO(id = mId, achievement = title))
                        clearDataAfterSaved()
                    }
                }

            }
            if (title.isEmpty()) binding.error.visibility = View.VISIBLE
        }

        binding.btnDelete.setOnClickListener {
            when (skillOrAchievement) {
                skill ->{
                    mDelegate.onTapDelete(mId, skill)
                }
                achievement -> {
                    mDelegate.onTapDelete(mId, achievement)
                }
            }
        }
    }

    private fun clearDataAfterSaved() {
        mId = System.currentTimeMillis()
        binding.etTitle.setText("")

        collapseCardView()
    }

    private fun collapseCardView() {
        binding.containerCollapsed.visibility = View.VISIBLE
        binding.containerExpanded.visibility = View.GONE
        binding.root.radius = 8F
    }

    private fun expandCardView() {
        binding.containerCollapsed.visibility = View.GONE
        binding.containerExpanded.visibility = View.VISIBLE
        binding.root.radius = 48F
    }

    private fun setUpError(editText: EditText, error: TextView) {

        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) error.visibility = View.VISIBLE
                else error.visibility = View.INVISIBLE
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setUpSkillAchiText(s: Int) {
        binding.tvDetailNameCollapsed.text = context.getString(s)
        binding.tvDetailNameExpanded.text = context.getString(s)
        binding.tvTitle.text = context.getString(s)
    }

}