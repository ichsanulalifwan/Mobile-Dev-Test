package com.app.mobiledevtest.ui.firstscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.mobiledevtest.databinding.FragmentFirstScreenBinding


class FirstScreenFragment : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            binding.run {

                // Button to check Palindrome text
                btnCheck.setOnClickListener {

                    // Get Palindrome text from user input
                    val palindrome = edPalindrome.text.toString()

                    // Check isPalindrome
                    if (validateInput(palindrome, 1)) {
                        if (isPalindrome(palindrome)) showDialog("isPalindrome")
                        else showDialog("not palindrome")
                    }
                }

                // Button to navigate to next screen
                btnNext.setOnClickListener {

                    // Get Name from user input
                    val name = edName.text.toString()

                    // Check name value before navigate
                    if (validateInput(name, 0)) {
                        val actionNext =
                            FirstScreenFragmentDirections.actionFirstScreenFragmentToSecondScreenFragment(
                                name,
                                null
                            )
                        findNavController().navigate(actionNext)
                    }
                }
            }
        }
    }

    /**
     * Validate User Name Input
     * when type == 0 -> check user name
     * type -- 1 -> check palindrome text
     */
    private fun validateInput(text: String, type: Int): Boolean {
        binding.run {
            if (text.isEmpty()) {
                when (type) {
                    0 -> {
                        edName.error = "Please input your name"
                        edName.requestFocus()
                    }
                    1 -> {
                        edPalindrome.error = "Please input your text"
                        edPalindrome.requestFocus()
                    }
                }

                return false
            }

            return true
        }
    }

    /**
     * Check if name isPalindrome or not
     */
    private fun isPalindrome(name: String): Boolean {

        var head = 0
        var tail = name.length - 1
        var cHead: Char
        var cTail: Char

        while (head <= tail) {
            cHead = name[head]
            cTail = name[tail]
            if (!Character.isLetterOrDigit(cHead)) {
                head++
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--
            } else {
                if (cHead.lowercaseChar() != cTail.lowercaseChar()) {
                    return false
                }
                head++
                tail--
            }
        }

        return true
    }

    /**
     * Show dialog palindrome check
     */
    private fun showDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setOnDismissListener { dialog ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}