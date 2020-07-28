package gt.tribal.app.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import gt.tribal.app.R
import gt.tribal.app.databinding.FragmentHomeBinding
import gt.tribal.app.domain.response.GeneralRateBean
import gt.tribal.app.domain.response.RateResponse
import gt.tribal.app.ui.activity.QrActivity
import gt.tribal.app.util.SharedPreference
import gt.tribal.app.viewmodel.HomeViewModel
import gt.tribal.core.coroutines.Result
import gt.tribal.core.extension.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var usdRate: GeneralRateBean
    private lateinit var eurRate: GeneralRateBean
    private lateinit var vefRate: GeneralRateBean
    private lateinit var cnyRate: GeneralRateBean


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(viewModel) {
            observe(homeLiveData, ::homeDataObserver)
        }
        viewModel.getHomeRates()

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.btnCalculate.setOnClickListener {
            if(binding.editAmount.text.toString().trim().isNotEmpty()){
                calculateRatesAndShow()
            }
        }

        binding.btnQr.setOnClickListener {

            if(binding.editAmount.text.toString().trim().isNotEmpty()){
                val intent = Intent(activity,QrActivity::class.java)
                intent.putExtra("content",binding.editAmount.text.toString().trim())
                startActivity(intent)
            }

        }
    }

    private fun homeDataObserver(result: Result<RateResponse>?) {
        when (result) {
            is Result.OnLoading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnCalculate.isEnabled = false
                binding.btnQr.isEnabled = false
            }
            is Result.OnSuccess -> {
                binding.progressBar.visibility = View.GONE
                usdRate = result.value.rates.usd
                eurRate = result.value.rates.eur
                vefRate = result.value.rates.vef
                cnyRate = result.value.rates.cny
                binding.btnCalculate.isEnabled = true
                binding.btnQr.isEnabled = true

            }
            is Result.OnError -> {
                binding.progressBar.visibility = View.GONE
                binding.btnCalculate.isEnabled = false
                binding.btnQr.isEnabled = false
                Toast.makeText(activity, getString(R.string.error_conection), Toast.LENGTH_LONG)
                    .show()

            }
            else -> {
                Toast.makeText(activity, getString(R.string.error_conection), Toast.LENGTH_LONG)
                    .show()
                binding.btnCalculate.isEnabled = false
                binding.btnQr.isEnabled = false

            }
        }
    }

    private fun calculateRatesAndShow() {
        var totalUsd = binding.editAmount.text.toString().toInt() * usdRate.value
        var totalEur = binding.editAmount.text.toString().toInt() * eurRate.value
        var totalVef = binding.editAmount.text.toString().toInt() * vefRate.value
        var totalCny = binding.editAmount.text.toString().toInt() * cnyRate.value

        binding.textRate.text = String.format(getString(R.string.format_rate), usdRate.value)
        binding.textRateEur.text = String.format(getString(R.string.format_rate), eurRate.value)
        binding.textRateBs.text = String.format(getString(R.string.format_rate), vefRate.value)
        binding.textRateYuan.text = String.format(getString(R.string.format_rate), cnyRate.value)

        binding.totalUsd.text = String.format(getString(R.string.format_total), totalUsd)
        binding.totalEur.text = String.format(getString(R.string.format_total), totalEur)
        binding.totalBs.text = String.format(getString(R.string.format_total), totalVef)
        binding.totalYuan.text = String.format(getString(R.string.format_total), totalCny)

    }
}
