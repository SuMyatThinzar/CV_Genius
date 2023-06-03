package com.smtz.cvgenius.presentation.details.workExperiences

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
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ViewPodWorkExperienceBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.domain.repository.CvModel
import java.util.*
import kotlin.math.exp

class WorkExperienceViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

//    var binding = ViewPodEducationDetailBinding.inflate(LayoutInflater.from(context), this)

    private lateinit var binding : ViewPodWorkExperienceBinding

    private lateinit var mDelegate: ButtonSaveWorkExpDelegate

    var selectedStartDate = ""
    var selectedEndDate = ""
    private var position = ""
    private var company = ""
    private var description: String? = null
    private var startDate: String?  = null
    private var endDate: String?  = null

    private var mWorkExpVO: WorkExperienceVO? = null
    private var mId: Long = System.currentTimeMillis()

    override fun onFinishInflate() {

        binding = ViewPodWorkExperienceBinding.bind(this)
        setUpListeners()
        super.onFinishInflate()
    }

    //Function to call from Activity
    fun setUpWorkExperienceViewPod(delegate: ButtonSaveWorkExpDelegate, changeBtnAdd: Boolean){
        if (changeBtnAdd) {
            binding.btnDelete.setImageResource(R.drawable.ic_add)
        }
        setDelegate(delegate)
    }

    fun setUpData(workExpVO: WorkExperienceVO) {
        mWorkExpVO = workExpVO
        bindData()
    }

    //Delegate setter
    private fun setDelegate(delegate: ButtonSaveWorkExpDelegate){
        this.mDelegate = delegate
    }

    private fun bindData() {
        mWorkExpVO?.let{
            mId = it.id
            binding.tvDetailName.text = "${it.position} at ${it.company}"
            binding.etWorkPosition.setText(it.position)
            binding.etCompany.setText(it.company)
            binding.etDescription.setText(it.description)
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
            position = binding.etWorkPosition.text.toString().trim()
            company = binding.etCompany.text.toString().trim()
            description = binding.etDescription.text.toString().trim()
            startDate = binding.startDate.text.toString().trim()

            endDate = if (binding.cbEndDate.isChecked) "Nowadays"
            else binding.endDate.text.toString().trim()

            if (position.isNotEmpty() && company.isNotEmpty()) {
                mDelegate.onTapSaveWorkExperience(
                    WorkExperienceVO(
                        id = mId,
                        position = position,
                        company = company,
                        description = description,
                        startDate = startDate,
                        endDate = endDate
                    )

                )
                mId = System.currentTimeMillis()
                binding.etWorkPosition.setText("")
                binding.etCompany.setText("")
                binding.etDescription.setText("")
                binding.startDate.text = ""
                binding.endDate.text = ""
                binding.cbEndDate.isChecked = false

                collapseCardView()
            }
            if (position.isEmpty())    binding.errorWorkPosition.visibility = View.VISIBLE
            if (company.isEmpty())    binding.errorCompany.visibility = View.VISIBLE
        }

        binding.btnDelete.setOnClickListener {
            mDelegate.onTapDelete(mId)
        }

        binding.cbEndDate.setOnCheckedChangeListener { _, checked ->

            if (checked) {
                binding.endDate.apply {
                    text = "Nowadays"
                    isClickable = false          // make btnDelete unclickable
                }
            } else {
                binding.endDate.apply {
                    text = selectedEndDate
                    isClickable = true
                }
            }
        }
//
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

        setUpError(binding.etWorkPosition, null, binding.errorWorkPosition)
        setUpError(binding.etCompany, null, binding.errorCompany)
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