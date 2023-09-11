package com.smtz.cvgenius.presentation.details.reference

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewPodReferencesBinding
import com.smtz.cvgenius.domain.model.ProjectDetailVO
import com.smtz.cvgenius.domain.model.ReferenceVO
import com.smtz.cvgenius.presentation.details.projectDetails.ButtonSaveProjectDelegate
import java.util.Calendar

class ReferenceViewPod  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

//    var binding = ViewPodEducationDetailBinding.inflate(LayoutInflater.from(context), this)

    private lateinit var binding : ViewPodReferencesBinding

    private lateinit var mDelegate: ButtonSaveReferenceDelegate

    private var referenceName = ""
    private var position: String?  = null
    private var companyName: String? = null
    private var emailAddress: String? = null
    private var phoneNumber: String?  = null
    private var others: String?  = null

    private var mReferenceVO: ReferenceVO? = null
    private var mId: Long = System.currentTimeMillis()

    override fun onFinishInflate() {

        binding = ViewPodReferencesBinding.bind(this)
        setUpListeners()
        super.onFinishInflate()
    }

    //Function to call from Activity
    fun setUpReferenceViewPod(delegate: ButtonSaveReferenceDelegate, changeBtnAdd: Boolean){
        if (changeBtnAdd) {  // if ViewPod is setUp from Activity else setUp from ViewHolder
            binding.btnDelete.setImageResource(R.drawable.ic_add)
            binding.btnDelete.isClickable = false                  // make btnDelete unclickable
            binding.containerExpanded.visibility = View.VISIBLE
        }
        setDelegate(delegate)
    }

    fun setUpData(referenceVO: ReferenceVO) {
        mReferenceVO = referenceVO
        bindData()
    }

    //Delegate setter
    private fun setDelegate(delegate: ButtonSaveReferenceDelegate){
        this.mDelegate = delegate
    }

    private fun bindData() {
        mReferenceVO?.let{
            mId = it.id
            binding.tvDetailName.text = it.referenceName
            binding.etReferenceName.setText(it.referenceName)
            binding.etPosition.setText(it.position)
            binding.etCompanyName.setText(it.companyName)
            binding.etEmail.setText(it.emailAddress)
            binding.etPhoneNumber.setText(it.phoneNumber)
            binding.etOthers.setText(it.others)
        }
    }

    private fun setUpListeners() {
        binding.containerCollapsed.setOnClickListener {
            expandCardView()
        }
        binding.tvDetailNameExpanded.setOnClickListener {
            collapseCardView()
        }

        binding.btnSave.setOnClickListener {
            referenceName = binding.etReferenceName.text.toString().trim()
            position = binding.etPosition.text.toString().trim()
            companyName = binding.etCompanyName.text.toString().trim()
            emailAddress = binding.etEmail.text.toString().trim()
            phoneNumber = binding.etPhoneNumber.text.toString().trim()
            others = binding.etOthers.text.toString().trim()

            if (referenceName.isNotEmpty()) {
                mDelegate.onTapSaveReference(
                    ReferenceVO(
                        id = mId,
                        referenceName = referenceName,
                        position = position,
                        companyName = companyName,
                        emailAddress = emailAddress,
                        phoneNumber = phoneNumber,
                        others = others,
                    )
                )

                mId = System.currentTimeMillis()
                binding.etReferenceName.setText("")
                binding.etPosition.setText("")
                binding.etEmail.setText("")
                binding.etPhoneNumber.setText("")
                binding.etOthers.setText("")

                collapseCardView()
            }

            if (referenceName.isEmpty())   binding.errorReferenceName.visibility = View.VISIBLE
        }

        binding.btnDelete.setOnClickListener {
            mDelegate.onTapDelete(mId)
        }

        setUpError(binding.etReferenceName, null, binding.errorReferenceName)
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

    private fun setUpError(editText: EditText?, textView: TextView?, error: TextView) {
        if (editText == null) {
            textView?.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty()) error.visibility = View.VISIBLE
                    else                   error.visibility = View.INVISIBLE
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}
            })
        } else {
            editText.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty()) error.visibility = View.VISIBLE
                    else                   error.visibility = View.INVISIBLE
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

}