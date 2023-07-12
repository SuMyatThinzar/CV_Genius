package com.smtz.cvgenius.presentation.details.educationDetails

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewPodEducationDetailBinding
import com.smtz.cvgenius.domain.model.EducationDetailVO
import java.util.*

class EducationDetailViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

//    var binding = ViewPodEducationDetailBinding.inflate(LayoutInflater.from(context), this)

    private lateinit var binding : ViewPodEducationDetailBinding

    private lateinit var mDelegate: ButtonSaveEducationDelegate

    var selectedStartDate = ""
    var selectedEndDate = ""
    private var dipName = ""
    private var levelOfEdu = ""
    private var uniName = ""
    private var startDate = ""
    private var endDate = ""
    private var credentialURL: String?  = null

    private var mEduVO: EducationDetailVO? = null
    private var mId: Long = System.currentTimeMillis()

    override fun onFinishInflate() {

        binding = ViewPodEducationDetailBinding.bind(this)
        setUpListeners()
        super.onFinishInflate()
    }

    //Function to call from Activity
    fun setUpEducationDetailViewPod(delegate: ButtonSaveEducationDelegate, changeBtnAdd: Boolean){
        if (changeBtnAdd) {
            binding.btnDelete.setImageResource(R.drawable.ic_add)
            binding.btnDelete.isClickable = false                  // make btnDelete unclickable
            binding.containerExpanded.visibility = View.VISIBLE
        }
        setDelegate(delegate)
    }

    //Function to call from ViewHolder
    fun setUpData(eduVO: EducationDetailVO) {
        mEduVO = eduVO
        bindData()
    }

    //Delegate setter
    private fun setDelegate(delegate: ButtonSaveEducationDelegate){
        this.mDelegate = delegate
    }

    private fun bindData() {
        mEduVO?.let{
            mId = it.id
            binding.tvDetailName.text = "${it.diplomaName} at ${it.schoolName}"
            binding.etDegreeDiplomaName.setText(it.diplomaName)
            binding.etLevelOfEdu.setText(it.levelOfEducation)
            binding.etUniversityName.setText(it.schoolName)
            binding.etCredentialURL.setText(it.credentialURL)
            binding.startDate.text = it.startDate
            binding.endDate.text = it.endDate
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
            dipName = binding.etDegreeDiplomaName.text.toString().trim()
            levelOfEdu = binding.etLevelOfEdu.text.toString().trim()
            uniName = binding.etUniversityName.text.toString().trim()
            credentialURL = binding.etCredentialURL.text.toString().trim()
            startDate = binding.startDate.text.toString().trim()

            endDate = if (binding.cbEndDate.isChecked) "Nowadays"
            else binding.endDate.text.toString().trim()

            if (dipName.isNotEmpty() && uniName.isNotEmpty() && startDate.isNotEmpty()) {
                if (endDate.isNotEmpty() || binding.cbEndDate.isChecked) {

                    mDelegate.onTapSaveEducation(
                        EducationDetailVO(
                            id = mId,
                            diplomaName = dipName,
                            levelOfEducation = levelOfEdu,
                            schoolName = uniName,
                            startDate = startDate,
                            endDate = endDate,
                            credentialURL = credentialURL
                        )
                    )
                    mId = System.currentTimeMillis()
                    binding.etDegreeDiplomaName.setText("")
                    binding.etLevelOfEdu.setText("")
                    binding.etUniversityName.setText("")
                    binding.etCredentialURL.setText("")
                    binding.startDate.text = ""
                    binding.endDate.text = ""
                    binding.cbEndDate.isChecked = false

                    collapseCardView()
                }
            }
            if (dipName.isEmpty())    binding.errorDegreeName.visibility = View.VISIBLE
            if (uniName.isEmpty())    binding.errorUniversityName.visibility = View.VISIBLE
            if (startDate.isEmpty())  binding.errorStartDate.visibility = View.VISIBLE
            if (endDate.isEmpty())    binding.errorEndDate.visibility = View.VISIBLE

        }

        binding.btnDelete.setOnClickListener {
            mDelegate.onTapDelete(mId)
        }

        binding.cbEndDate.setOnCheckedChangeListener { _, checked ->
//            binding.endDate.isClickable = !binding.cbEndDate.isChecked
//            if (checked) binding.endDate.text = "Nowadays"
//            else         binding.endDate.text = selectedEndDate

            if (checked) {
                binding.endDate.apply {
                    text = "Nowadays"
                    isClickable = false          // make cbEndDate unclickable
                }
            } else {
                binding.endDate.apply {
                    text = selectedEndDate
                    isClickable = true
                }
            }
        }

        binding.startDate.setOnClickListener {
            val dialogView = LayoutInflater.from(binding.root.context).inflate(R.layout.dialog_month_year_picker, null)
            showDatePickerDialog(dialogView, binding.startDate, true).apply {
                setView(dialogView)
                show()
            }
        }
        binding.endDate.setOnClickListener {
            val dialogView = LayoutInflater.from(binding.root.context).inflate(R.layout.dialog_month_year_picker, null)
            showDatePickerDialog(dialogView, binding.endDate, false).apply {
                setView(dialogView)
                show()
            }
        }

        setUpError(binding.etDegreeDiplomaName, null, binding.errorDegreeName)
//        setUpError(binding.etLevelOfEdu, null, binding.errorLevelOfEdu)
        setUpError(binding.etUniversityName, null, binding.errorUniversityName)
        setUpError(null, binding.startDate, binding.errorStartDate)
        setUpError(null, binding.endDate, binding.errorEndDate)
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

    private fun showDatePickerDialog(dialogView: View, bindView: TextView, startDate: Boolean): AlertDialog {

        // Get the current year and month
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

        val monthPicker = dialogView.findViewById<NumberPicker>(R.id.month_picker)
        monthPicker.minValue = 0
        monthPicker.maxValue = 11
        monthPicker.displayedValues = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        monthPicker.value = currentMonth   // auto selected current month

        val yearPicker = dialogView.findViewById<NumberPicker>(R.id.year_picker)
        yearPicker.minValue = 1970
        yearPicker.maxValue = Calendar.getInstance().get(Calendar.YEAR)  // current Year
        yearPicker.wrapSelectorWheel = false
        yearPicker.value = currentYear   // auto selected current year

        // auto select last selected Month and Year
        if (startDate) {
            if (selectedStartDate != "") {
                val dateParts = selectedStartDate.split("/")
                val month = dateParts[0].toInt()
                val year = dateParts[1].toInt()
                monthPicker.value = month - 1
                yearPicker.value = year
            }
        } else {
            if (selectedEndDate != "") {
                val dateParts = selectedEndDate.split("/")
                val month = dateParts[0].toInt()
                val year = dateParts[1].toInt()
                monthPicker.value = month
                yearPicker.value = year
            }
        }

        val dialog = AlertDialog.Builder(binding.root.context, R.style.CustomAlertDialogTheme)
            .setTitle("Select month and year")
            .setPositiveButton("OK") { _, _ ->
                val month = monthPicker.value + 1
                val year = yearPicker.value
                val date = "$month/$year"
                bindView.text = date

                if (startDate) {
                    selectedStartDate = date
                } else {
                    selectedEndDate = date
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        return dialog
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