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
    fun initialGuestStateDisplayed() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule.onNodeWithText("Статус: Гость")
            .assertIsDisplayed()

        rule.onNodeWithText("Перейти на 2 экран")
            .assertIsDisplayed()

        rule.onNodeWithText("Счётчик: 0")
            .assertIsDisplayed()

    }

    @Test
    fun loginToggleChangesStatus() {
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
    fun counterIncrements() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule.onNodeWithText("+")
            .performClick()

        rule.onNodeWithText("Счётчик: 1")
            .assertIsDisplayed()
    }

    @Test
    fun counterDecrements() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule.onNodeWithText("+")
            .performClick()

        rule.onNodeWithText("Счётчик: 1")
            .assertIsDisplayed()

        rule.onNodeWithText("-")
            .performClick()

        rule.onNodeWithText("Счётчик: 0")
            .assertIsDisplayed()
    }

    @Test
    fun errorShownOnNegativeCounterBlocked() {
        rule.setContent { FirstScreen(onGoSecond = {}) }

        rule.onNodeWithText("Счётчик: 0")
            .assertIsDisplayed()

        rule.onNodeWithText("-")
            .performClick()

        rule.onNodeWithText("Ошибка: нельзя уйти в минус")
            .assertIsDisplayed()
    }

    @Test
    fun switchToSecondScreen() {
        var clicked = false

        rule.setContent { FirstScreen(onGoSecond = { clicked = true }) }

        rule.onNodeWithText("Перейти на 2 экран")
            .performClick()
        assert(clicked)
    }

    @Test
    fun combinedUserFlowTest(){
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





















