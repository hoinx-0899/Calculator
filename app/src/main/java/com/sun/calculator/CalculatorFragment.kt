package com.sun.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment : Fragment(), View.OnClickListener {
    private var isAdd = false
    private var isSubtraction = false
    private var isMultiply = false
    private var isDivide = false
    private var isDot = false
    private var mFirstValue: Float = 0.0f
    private var mSecondValue: Float = 0.0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerListeners()
    }

    private fun registerListeners() {
        btnZero.setOnClickListener(this)
        btnMinusValue.setOnClickListener(this)
        btnOne.setOnClickListener(this)
        btnTwo.setOnClickListener(this)
        btnThree.setOnClickListener(this)
        btnAdd.setOnClickListener(this)
        btnFour.setOnClickListener(this)
        btnFive.setOnClickListener(this)
        btnSix.setOnClickListener(this)
        btnSeven.setOnClickListener(this)
        btnEight.setOnClickListener(this)
        btnNine.setOnClickListener(this)
        btnAc.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnPercentage.setOnClickListener(this)
        btnDivide.setOnClickListener(this)
        btnSubtraction.setOnClickListener(this)
        btnEqual.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnPercentage -> {
                if (!checkOperation()) {
                    tvResult.text = (tvResult.text.toString().toDouble() / 100).toString()
                }

            }
            R.id.btnDivide -> {
                if (!checkOperation()) {
                    mFirstValue = tvResult.text.toString().toFloat()
                    tvResult.text = ""
                    isDivide = true
                    isMultiply=false
                    isAdd=false
                    isSubtraction=false
                }
            }
            R.id.btnAdd -> {
                if (!checkOperation()) {
                    mFirstValue = tvResult.text.toString().toFloat()
                    tvResult.text = ""
                    isAdd = true
                    isDivide = false
                    isMultiply=false
                    isSubtraction=false
                }
            }
            R.id.btnSubtraction -> {
                if (!checkOperation()) {
                    mFirstValue = tvResult.text.toString().toFloat()
                    isDivide = false
                    isMultiply=false
                    isAdd=false
                    isSubtraction=true
                    tvResult.text = ""
                }

            }
            R.id.btnMultiply -> {
                if (!checkOperation()) {
                    mFirstValue = tvResult.text.toString().toFloat()
                    isDivide = false
                    isMultiply=true
                    isAdd=false
                    isSubtraction=false
                    tvResult.text = ""
                }
            }

            R.id.btnZero -> {
                appendText(0)
            }
            R.id.btnAc -> {
                tvResult.text = ""

            }
            R.id.btnMinusValue -> {
                if (!checkEndWith()) {
                    isDot = true
                    tvResult.text = tvResult.text.toString() + getString(R.string.dot)

                }

            }
            R.id.btnOne -> {
                appendText(1)
            }
            R.id.btnTwo -> {
                appendText(2)
            }
            R.id.btnThree -> {
                appendText(3)
            }
            R.id.btnFour -> {
                appendText(4)
            }
            R.id.btnFive -> {
                appendText(5)
            }
            R.id.btnSix -> {
                appendText(6)
            }
            R.id.btnSeven -> {
                appendText(7)
            }
            R.id.btnEight -> {
                appendText(8)
            }
            R.id.btnNine -> {
                appendText(9)

            }
            R.id.btnEqual -> {
                if (!checkOperation()) {
                    mSecondValue = tvResult.text.toString().toFloat()
                    when {
                        isAdd -> tvResult.text = (mFirstValue + mSecondValue).toString()
                        isDivide -> tvResult.text = (mFirstValue / mSecondValue).toString()
                        isMultiply -> tvResult.text = (mFirstValue * mSecondValue).toString()
                        isSubtraction -> tvResult.text = (mFirstValue - mSecondValue).toString()
                    }

                }


            }

        }
    }

    private fun checkOperation(): Boolean = checkTextResult() || checkEndWith()

    private fun checkEndWith(): Boolean = checkEndWith(getString(R.string.sub)) ||
            checkEndWith(getString(R.string.plus))
            || checkEndWith(getString(R.string.divide))
            || checkEndWith(getString(R.string.multiply))

    private fun checkEndWith(end: String): Boolean = tvResult.text.toString().endsWith(end)

    private fun checkTextResult(): Boolean = TextUtils.isEmpty(tvResult.text.toString())

    @SuppressLint("SetTextI18n")
    private fun appendText(number: Int) {
        tvResult.text = tvResult.text.toString() + number
    }

}