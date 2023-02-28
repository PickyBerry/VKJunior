package com.pickyberry.vkjunior

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import com.pickyberry.vkjunior.databinding.FragmentCallBinding
import kotlin.system.exitProcess


//Экран, который нужно было сверстать
class CallFragment : Fragment() {

    private lateinit var binding: FragmentCallBinding

    // Вкл/выкл камеры и микрофон
    private var toggleCamera = true
    private var toggleMic = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCallBinding.inflate(layoutInflater)


        //данные о собеседнике приходят из списка контактов
        val speakerName=requireArguments().getString("name")!!
        val speakerPhoto=requireArguments().getInt("photo")
        setupSpeakers(speakerName,speakerPhoto)
        setOnClickListeners()
        return binding.root
    }

    //заполняем данные о собеседниках
    private fun setupSpeakers(speakerName:String,speakerPhoto:Int) {
        binding.ivSpeaker1.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.user_00,
                null
            )
        )
        var builder = Palette.Builder((binding.ivSpeaker1.drawable as BitmapDrawable).bitmap)
        var palette = builder.generate()
        binding.cvSpeaker1.setCardBackgroundColor(palette.dominantSwatch!!.rgb)
        binding.tvSpeaker1.text = getString(R.string.you)


        binding.ivSpeaker2.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                speakerPhoto,
                null
            )
        )
        builder = Palette.Builder((binding.ivSpeaker2.drawable as BitmapDrawable).bitmap)
        palette = builder.generate()
        binding.cvSpeaker2.setCardBackgroundColor(palette.dominantSwatch!!.rgb)
        binding.tvSpeaker2.text = speakerName
    }

    //Обработка нажатий на кнопки в соответствии с заданием
    private fun setOnClickListeners() {
        binding.btnMessages.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
            startActivity(intent)
        }

        binding.btnContacts.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding.btnSwap.setOnClickListener {
            val speaker1 = binding.speakers[0]
            val speaker2 = binding.speakers[1]
            binding.speakers.removeAllViews()
            binding.speakers.addView(speaker2)
            binding.speakers.addView(speaker1)
        }

        binding.btnVideocam.setOnClickListener {
            toggleCamera = !toggleCamera
            if (toggleCamera) {
                binding.cvVideocam.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.gray,
                        null
                    )
                )
                binding.btnVideocam.setBackgroundResource(R.drawable.baseline_videocam_48)
            } else {
                binding.cvVideocam.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                binding.btnVideocam.setBackgroundResource(R.drawable.baseline_videocam_off_48)
            }
        }

        binding.btnMic.setOnClickListener {
            toggleMic = !toggleMic
            if (toggleMic) {
                binding.cvMic.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.gray,
                        null
                    )
                )
                binding.btnMic.setBackgroundResource(R.drawable.baseline_mic_16)
                binding.tvSpeaker1.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_mic_16,
                    0
                )
            } else {
                binding.cvMic.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                binding.btnMic.setBackgroundResource(R.drawable.baseline_mic_off_48)
                binding.tvSpeaker1.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_mic_off_16,
                    0
                )
            }
        }


        binding.btnWave.setOnClickListener {
            AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
                .setTitle("Привет?")
                .setMessage("привет")
                .setPositiveButton("Привет :)", null)
                .setNegativeButton("Не привет :(", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }

        binding.btnHangup.setOnClickListener {
            activity?.finish()
            exitProcess(0)
        }
    }

}