package com.example.signupapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeExampleTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "signup") {
                    composable("signup") {
                        SignupScreen(navController)
                    }
                    composable("welcome/{username}/{email}") { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username")
                        val email = backStackEntry.arguments?.getString("email")
                        WelcomeScreen(username, email)
                    }
                }
            }
        }
    }
}

@Composable
fun SignupScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Signup", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = username,
                    innerTextField = innerTextField,
                    label = { Text("Username") },
                    enabled = true,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    contentPadding = PaddingValues()
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = email,
                    innerTextField = innerTextField,
                    label = { Text("Email") },
                    enabled = true,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    contentPadding = PaddingValues()
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = password,
                    innerTextField = innerTextField,
                    label = { Text("Password") },
                    enabled = true,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    contentPadding = PaddingValues()
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = confirmPassword,
                    innerTextField = innerTextField,
                    label = { Text("Confirm Password") },
                    enabled = true,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    contentPadding = PaddingValues()
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (password == confirmPassword) {
                    navController.navigate("welcome/$username/$email")
                } else {
                    // Handle error
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }
    }
}

@Composable
fun WelcomeScreen(username: String?, email: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome, $username!", style = MaterialTheme.typography.h4)
        Text(text = "Your email is $email", style = MaterialTheme.typography.h5)
    }
}
