package com.smtz.cvgenius.presentation.details.personalDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.smtz.cvgenius.common.components.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityPersonalDetailBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.PersonalDetailVO
import com.smtz.cvgenius.domain.repository.CvModel


class PersonalDetailActivity : BaseActivity<ActivityPersonalDetailBinding>() {

    private val ANIMATION_DURATION = 200L
    private var mToolbarHeight = 330
    private var isExpanded = true

    private var mCvId: Long? = null
    private var mCvVO: CvVO? = null
    private var mPersonalDetail: PersonalDetailVO? = null
    private var personalId: Long = 0

    private var mCvModel: CvModel = CvModelImpl

    override val binding: ActivityPersonalDetailBinding by lazy {
        ActivityPersonalDetailBinding.inflate(layoutInflater)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, PersonalDetailActivity::class.java)
//            intent.putExtra(EXTRA_CV, cvVO)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        mCvVO = intent.getSerializableExtra(EXTRA_CV) as CvVO

        mCvVO = CvSingleton.instance.cvVO

//        setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)
        setUpListener()
        setUpData()
    }

    private fun setUpData() {
        // set up Id
        personalId = if (mCvVO?.personalDetails != null) mCvVO?.personalDetails!!.id else System.currentTimeMillis()

        // set up editText
        mCvVO?.personalDetails?.let {
            binding.etFirstName.setText(it.firstName)
            binding.etContact.setText(it.contact)
            binding.etLastName.setText(it.lastName)
            binding.etNationality.setText(it.nationality)
            binding.etDateOfBirth.setText(it.dateOfBirth)
            binding.etEmail.setText(it.email)
            binding.etAddress.setText(it.address)
            binding.etProfessionalTitle.setText(it.professionalTitle)
        }
    }

    private fun setUpListener() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSave.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val contact = binding.etContact.text.toString().trim()
            val website = binding.etWebsite.text.toString().trim()
            val nationality = binding.etNationality.text.toString().trim()
            val dateOfBirth = binding.etDateOfBirth.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()
            val professionalTitle = binding.etProfessionalTitle.text.toString().trim()

            if (firstName.isNotEmpty() && contact.isNotEmpty()) {

                val personalDetailVO = PersonalDetailVO(
                    id = personalId,
                    firstName = firstName,
                    lastName = lastName,
                    contact = contact,
                    nationality = nationality,
                    dateOfBirth = dateOfBirth,
                    gender = website,
                    email = email,
                    address = address,
                    professionalTitle = professionalTitle
                )
                mCvVO?.personalDetails = personalDetailVO

//                mCvVO?.let { it1 -> mCvModel.insertCV(it1) }

//                val intent = Intent(this,CreateCvActivity::class.java)
//                    .putExtra(EXTRA_RESULT, mCvVO)
//                setResult(Activity.RESULT_OK, intent)

                finish()
            }

            if (firstName.isEmpty()) binding.errorName.visibility = View.VISIBLE
            if (contact.isEmpty()) binding.errorContact.visibility = View.VISIBLE

        }

        setUpError(binding.etFirstName, binding.errorName)
        setUpError(binding.etContact, binding.errorContact)

        // spinner
//        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                (view as TextView).setTextColor(resources.getColor(R.color.colorSecondaryText)) //Change selected text color
//                gender = view.text as String?
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }


        // scroll to hide show
//        binding.nestedScrollView.viewTreeObserver
//            .addOnScrollChangedListener(object : OnScrollChangedListener {
//                var y = 0f
//                override fun onScrollChanged() {
//                    if (binding.nestedScrollView.scrollY > y && mToolbarHeight == binding.frameLayout.height) {
//                        collapseToolbar()
//                        isExpanded = false
//                    }
//                    if (mToolbarHeight > binding.frameLayout.height && binding.nestedScrollView.scrollY == 0 && !isExpanded) {
//                        expandToolbar()
//                        isExpanded = true
//                    }
//                    y = binding.nestedScrollView.scrollY.toFloat()
//                }
//            })

    }

//    private fun collapseToolbar() {
//
//        if (mToolbarHeight == binding.frameLayout.height) {
//
//            setUpTitleAndButton(
//                expand = false,
//                binding.tvTitle,
//                binding.btnBack,
//                binding.frameLayout
//            )
//
//            val animator = ValueAnimator.ofInt(binding.frameLayout.height, mToolbarHeight / 2)
//            animator.duration = ANIMATION_DURATION
//            animator.interpolator = AccelerateInterpolator()
//            animator.addUpdateListener {
//                val value = it.animatedValue as Int
//                binding.frameLayout.layoutParams.height = value
//                binding.frameLayout.requestLayout()
//            }
//            animator.start()
//        }
//    }

//    private fun expandToolbar() {
//
//        if (mToolbarHeight > binding.frameLayout.height) {
//
//            setUpTitleAndButton(
//                expand = true,
//                binding.tvTitle,
//                binding.btnBack,
//                binding.frameLayout
//            )
//
//            val animator = ValueAnimator.ofInt(binding.frameLayout.height, mToolbarHeight)
//            animator.duration = ANIMATION_DURATION
//            animator.interpolator = DecelerateInterpolator()
//            animator.addUpdateListener {
//                val value = it.animatedValue as Int
//                binding.frameLayout.layoutParams.height = value
//                binding.frameLayout.requestLayout()
//            }
////            animator.addListener(object : AnimatorListenerAdapter() {
////                override fun onAnimationEnd(animation: Animator) {
////                    isAnimating = false
////                }
////            })
//            animator.start()
//        }
//    }

//    private fun setUpEditorActionListener(firstEt: TextInputEditText, secondEt: TextInputEditText) {
//        firstEt.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_NEXT) {
//                secondEt.requestFocus()
//                return@setOnEditorActionListener true
//            }
//            return@setOnEditorActionListener false
//        }
//    }

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

}