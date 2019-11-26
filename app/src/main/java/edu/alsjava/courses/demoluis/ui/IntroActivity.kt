package edu.alsjava.courses.demoluis.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import edu.alsjava.courses.demoluis.R
import edu.alsjava.courses.demoluis.adapters.IntroPageAdapter
import edu.alsjava.courses.demoluis.utils.SharedPreferencesTool
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    private lateinit var introPageAdapter: IntroPageAdapter

    private var layouts: IntArray = intArrayOf(
            R.layout.intro_page_1,
            R.layout.intro_page_2
    )

    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
            object : ViewPager.OnPageChangeListener {

                override fun onPageSelected(position: Int) {
                    addBottomDots(position)
                    if (position < layouts.size - 1) {
                        if (position == 0) {
                            btnSkip.visibility = View.VISIBLE
                        } else {
                            btnSkip.visibility = View.GONE
                        }
                        btnNext.text = getString(R.string.next)
                    } else {
                        btnNext.text = getString(R.string.start)
                    }
                }

                override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
                }

                override fun onPageScrollStateChanged(arg0: Int) {
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        addBottomDots(0)
        btnSkip.setOnClickListener {
            openPrivacy()
        }
        btnNext.setOnClickListener {
            val current = vpContent.currentItem + 1
            if (current < layouts.size) {
                vpContent.currentItem = current
            } else {
                openPrivacy()
            }
        }
        introPageAdapter = IntroPageAdapter(this, layouts)
        vpContent.adapter = introPageAdapter
        vpContent.addOnPageChangeListener(viewPagerPageChangeListener)
    }

    private fun openPrivacy() {
        val sharedPreferencesTool = SharedPreferencesTool(this)
        sharedPreferencesTool.showIntro(false)
        val intent = Intent(this, PrivacyActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun addBottomDots(currentPage: Int) {
        llDots.removeAllViews()
        for (i in layouts.indices) {
            val dot = TextView(this)
            dot.text = HtmlCompat.fromHtml("&#8226;", HtmlCompat.FROM_HTML_MODE_COMPACT)
            dot.textSize = 35f
            dot.setTextColor(
                    ContextCompat.getColor(
                            this,
                            R.color.dot_light_screen
                    )
            )
            llDots.addView(dot)
        }
        markDot(currentPage)
    }

    private fun markDot(currentPage: Int) {
        if (llDots.size > 0) {
            (llDots[currentPage] as TextView).setTextColor(
                    ContextCompat.getColor(
                            this,
                            R.color.dot_dark_screen
                    )
            )
        }
    }
}