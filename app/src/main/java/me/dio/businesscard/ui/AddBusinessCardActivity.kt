package me.dio.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import me.dio.businesscard.App
import me.dio.businesscard.R
import me.dio.businesscard.data.BusinessCard
import me.dio.businesscard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListener()

        val colors = resources.getStringArray(R.array.colors)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, colors)
        binding.actvColor.setAdapter(arrayAdapter)
    }



    private fun insertListener(){
        binding.btClosedButton.setOnClickListener{
            finish()
        }

        binding.btnConfirm.setOnClickListener{
            val color = binding.tilColor.editText?.text.toString()

            val tradutor = when(color){
                "Branco" -> "#FFFFFF"
                "Azul" -> "#6887F9"
                "Verde" -> "#89F968"
                "Vermelho" -> "#F93939"
                "Cinza" -> "#C1AFAF"
                "Roxo" -> "#BB42CC"
                "Amarelo" -> "#E7EA2D"
                "Laranja" -> "#F9AD2A"
                else -> "#FFFFFF"
            }

            val businessCard = BusinessCard(
                name = binding.tilName.editText?.text.toString(),
                cellphone = binding.tilCellphone.editText?.text.toString(),
                mail = binding.tilMail.editText?.text.toString(),
                company = binding.tilCompany.editText?.text.toString(),
                background = tradutor
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}