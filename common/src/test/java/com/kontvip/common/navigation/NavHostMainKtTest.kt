package com.kontvip.common.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class NavHostMainKtTest {

    @Test
    fun `NavController isResumed should return true when currentBackStackEntry lifecycle state is RESUMED`() {
        val navController = mockNavController(lifecycleState = Lifecycle.State.RESUMED)
        assertTrue(navController.isResumed())
    }

    @Test
    fun `NavController isResumed should return false when currentBackStackEntry lifecycle state is not RESUMED`() {
        val navController = mockNavController(lifecycleState = Lifecycle.State.CREATED)
        assertFalse(navController.isResumed())
    }

    @Test
    fun `NavController navigateIfResumed should navigate when currentBackStackEntry lifecycle state is RESUMED`() {
        val navController = mockNavController(lifecycleState = Lifecycle.State.RESUMED)
        val route: Any = "destination"

        navController.navigateIfResumed(route)
        verify(navController).navigate(route)
    }

    @Test
    fun `NavController navigateIfResumed should not navigate when currentBackStackEntry lifecycle state is not RESUMED`() {
        val navController = mockNavController(lifecycleState = Lifecycle.State.CREATED)
        val route = "destination"
        navController.navigateIfResumed(route)
        verify(navController, never()).navigate(route)
    }

    @Test
    fun `NavController popBackStackIfResumed should pop back stack when currentBackStackEntry lifecycle state is RESUMED`() {
        val navController = mockNavController(lifecycleState = Lifecycle.State.RESUMED)
        navController.popBackStackIfResumed()
        verify(navController).popBackStack()
    }

    @Test
    fun `NavController popBackStackIfResumed should not pop back stack when currentBackStackEntry lifecycle state is not RESUMED`() {
        val navController = mockNavController(lifecycleState = Lifecycle.State.CREATED)
        navController.popBackStackIfResumed()
        verify(navController, never()).popBackStack()
    }

    private fun mockNavController(lifecycleState: Lifecycle.State): NavController {
        val navController = mock(NavController::class.java)
        val backStackEntry = mock(NavBackStackEntry::class.java)
        val lifecycle = mock(Lifecycle::class.java)

        `when`(backStackEntry.lifecycle).thenReturn(lifecycle)
        `when`(lifecycle.currentState).thenReturn(lifecycleState)
        `when`(navController.currentBackStackEntry).thenReturn(backStackEntry)

        return navController
    }

}