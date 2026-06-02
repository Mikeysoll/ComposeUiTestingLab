package com.example.composeui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import com.example.composeui.screen.FirstScreen

class FirstScreenTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun start_state_should_show_guest() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule.onNodeWithText("Статус: Гость")
            .assertIsDisplayed()

        rule.onNodeWithText("Перейти на 2 экран")
            .assertIsDisplayed()

        rule.onNodeWithText("Cчётчик: 0")
            .assertIsDisplayed()

    }

    @Test
    fun click_login_button_should_change_status() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule.onNodeWithText("Войти / Выйти")
            .performClick()
        rule.onNodeWithText("Статус: В системе")
            .assertIsDisplayed()

        rule.onNodeWithText("Войти / Выйти")
            .performClick()
        rule.onNodeWithText("Статус: Гость")
            .assertIsDisplayed()
    }

    @Test
    fun counter_should_increment() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule
            .onNodeWithText("+")
            .performClick()

        rule
            .onNodeWithText("Счётчик: 1")
            .assertIsDisplayed()
    }

    @Test
    fun counter_should_decrement() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule
            .onNodeWithText("+")
            .performClick()

        rule
            .onNodeWithText("Счётчик: 1")
            .assertIsDisplayed()

        rule
            .onNodeWithText("-")
            .performClick()

        rule
            .onNodeWithText("Счётчик: 0")
            .assertIsDisplayed()
    }

    @Test
    fun error_should_show_when_counter_below_zero_blocked() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule
            .onNodeWithText("Счётчик: 0")
            .assertIsDisplayed()

        rule
            .onNodeWithText("-")
            .performClick()

        rule
            .onNodeWithText("Ошибка: нельзя уйти в минус")
            .assertIsDisplayed()
    }

    @Test
    fun switchTheScreen() {
        var clicked = false

        rule.setContent { FirstScreen(onGoSecond = { clicked = true }) }

        rule.onNodeWithText("Перейти на 2 экран")
            .performClick()
        assert(clicked)
    }

    @Test
    fun combinationTest(){
        rule.setContent { FirstScreen (onGoSecond ={}) }
        rule.onNodeWithText("Войти / Выйти")
            .performClick()

        rule.onNodeWithText("Статус: В системе")
            .assertIsDisplayed()

        val node = rule.onNodeWithText("+")
        repeat(5) {
            node.performClick()
        }
        rule.onNodeWithText("Счётчик: 5")
            .assertIsDisplayed()
    }
}





















