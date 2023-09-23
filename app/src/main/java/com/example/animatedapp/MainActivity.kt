package com.example.animatedapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    private lateinit var bearAnimation: LottieAnimationView
    private lateinit var studyAnimation: LottieAnimationView
    private lateinit var shapesAnimation: LottieAnimationView
    private lateinit var gameAnimation: LottieAnimationView
    private lateinit var scaleAnimation: LottieAnimationView
    private lateinit var changeAnimationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bearAnimation = findViewById(R.id.bearDance)
        studyAnimation = findViewById(R.id.studyPlan)
        shapesAnimation = findViewById(R.id.shapesDance)
        gameAnimation = findViewById(R.id.game)
        scaleAnimation = findViewById(R.id.scaleAnimation)
        changeAnimationButton = findViewById(R.id.changeAnimation)

        bearAnimation.visibility = View.VISIBLE
        bearAnimation.playAnimation()

        studyAnimation.visibility = View.GONE
        shapesAnimation.visibility = View.GONE
        gameAnimation.visibility = View.GONE
        scaleAnimation.visibility = View.GONE

        bearAnimation.setOnClickListener {
            showPopup("Bear Animation")
            bearAnimation.playAnimation()
        }

        studyAnimation.setOnClickListener {
            showPopup("Study Animation")
            studyAnimation.playAnimation()
        }

        shapesAnimation.setOnClickListener {
            showPopup("Shapes Animation")
            shapesAnimation.playAnimation()
        }

        gameAnimation.setOnClickListener {
            showPopup("Game Animation")
            gameAnimation.playAnimation()
        }

        scaleAnimation.setOnClickListener {
            showPopup("Scale Animation")
            scaleAnimation.playAnimation()
        }

        changeAnimationButton.setOnClickListener {
            if (bearAnimation.visibility == View.VISIBLE) {
                bearAnimation.visibility = View.GONE
                studyAnimation.visibility = View.VISIBLE
                studyAnimation.playAnimation()
                changeBackgroundColor("#FF0000")
                enableAnimationMovement(studyAnimation)
            } else if (studyAnimation.visibility == View.VISIBLE) {
                studyAnimation.visibility = View.GONE
                shapesAnimation.visibility = View.VISIBLE
                shapesAnimation.playAnimation()
                changeBackgroundColor("#00FF00")
                enableAnimationMovement(shapesAnimation)
            } else if (shapesAnimation.visibility == View.VISIBLE) {
                shapesAnimation.visibility = View.GONE
                gameAnimation.visibility = View.VISIBLE
                gameAnimation.playAnimation()
                changeBackgroundColor("#0000FF")
                enableAnimationMovement(gameAnimation)
            } else if (gameAnimation.visibility == View.VISIBLE) {
                gameAnimation.visibility = View.GONE
                scaleAnimation.visibility = View.VISIBLE
                scaleAnimation.playAnimation()
                changeBackgroundColor("#FFFF00")
                enableAnimationMovement(scaleAnimation)
            } else if (scaleAnimation.visibility == View.VISIBLE) {
                scaleAnimation.visibility = View.GONE
                bearAnimation.visibility = View.VISIBLE
                bearAnimation.playAnimation()
                changeBackgroundColor("#FF55FF")
                enableAnimationMovement(bearAnimation)
            }
        }
    }

    private fun changeBackgroundColor(color: String) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            window.decorView.setBackgroundColor(Color.parseColor(color))
        }, 1000)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun enableAnimationMovement(animationView: LottieAnimationView) {
        animationView.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.tag = PointF(event.rawX, event.rawY)
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = event.rawX - (view.tag as PointF).x
                    val dy = event.rawY - (view.tag as PointF).y

                    view.x += dx
                    view.y += dy

                    view.tag = PointF(event.rawX, event.rawY)
                }
            }
            true
        }
    }

    private fun showPopup(animationName: String) {
        val popupText = "This is the $animationName"
        Toast.makeText(this, popupText, Toast.LENGTH_SHORT).show()
    }
}
