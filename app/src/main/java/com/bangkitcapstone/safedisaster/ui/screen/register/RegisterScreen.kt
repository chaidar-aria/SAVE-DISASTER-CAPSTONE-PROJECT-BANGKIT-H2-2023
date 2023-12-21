package com.bangkitcapstone.safedisaster.ui.screen.register

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.ui.navigation.Destination
import com.bangkitcapstone.safedisaster.ui.theme.BrownDark
import com.bangkitcapstone.safedisaster.ui.theme.BrownSemiLight
import com.bangkitcapstone.safedisaster.ui.theme.BrownVeryLight
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit, navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = BrownVeryLight // Use the Color class to represent the color
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConstraintLayout {
                    val (image, loginForm) = createRefs()
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(280.dp)
                            .constrainAs(image) {
                                top.linkTo(loginForm.top)
                                bottom.linkTo(loginForm.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        HeaderView()
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp)
                            .constrainAs(loginForm) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)
                        ) {

                            val loginText = "Pendaftaran Akun"
                            val loginWord = "Pendaftaran"
                            val loginAnnotatedString = buildAnnotatedString {
                                append(loginText)
                                addStyle(
                                    style = SpanStyle(
                                        color = BrownDark,
                                        fontWeight = FontWeight.Normal,
                                    ),
                                    start = 0,
                                    end = loginText.length
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = BrownDark,
                                        fontWeight = FontWeight.Medium,
                                    ),
                                    start = 0,
                                    end = loginWord.length
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 20.dp),
                                text = loginAnnotatedString,
                                textAlign = TextAlign.Center,
                                fontSize = 22.sp,
                            )

                            Text(
                                text = "Alamat Email",
                                style = MaterialTheme.typography.subtitle1.copy(color = BrownDark),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                value = email,
                                onValueChange = { email = it },
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                placeholder = { androidx.compose.material.Text(text = "Alamat Email") },
                                leadingIcon = {
                                    Row(
                                        modifier = Modifier.wrapContentWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        content = {
                                            Image(
                                                modifier = Modifier
                                                    .padding(start = 10.dp, end = 10.dp)
                                                    .size(18.dp),
                                                painter = painterResource(id = R.drawable.email),
                                                colorFilter = ColorFilter.tint(BrownDark),
                                                contentDescription = "custom_text_field"
                                            )
                                            Canvas(
                                                modifier = Modifier.height(24.dp)
                                            ) {
                                                // Allows you to draw a line between two points (p1 & p2) on the canvas.
                                                drawLine(
                                                    color = Color.LightGray,
                                                    start = Offset(0f, 0f),
                                                    end = Offset(0f, size.height),
                                                    strokeWidth = 2.0F
                                                )
                                            }
                                        }
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = BrownDark,
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedLabelColor = Color.White,
                                    trailingIconColor = Color.White,
                                ),
                                shape = RoundedCornerShape(10.dp),
                                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                                visualTransformation = VisualTransformation.None
                            )

                            Text(
                                text = "Kata Sandi",
                                style = MaterialTheme.typography.subtitle1.copy(color = BrownDark),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                value = password,
                                onValueChange = { password = it },
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                placeholder = { androidx.compose.material.Text(text = "Kata Sandi") },
                                leadingIcon = {
                                    Row(
                                        modifier = Modifier.wrapContentWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        content = {
                                            Image(
                                                modifier = Modifier
                                                    .padding(start = 10.dp, end = 10.dp)
                                                    .size(18.dp),
                                                painter = painterResource(id = R.drawable.password),
                                                colorFilter = ColorFilter.tint(BrownDark),
                                                contentDescription = "custom_text_field"
                                            )
                                            Canvas(
                                                modifier = Modifier.height(24.dp)
                                            ) {
                                                // Allows you to draw a line between two points (p1 & p2) on the canvas.
                                                drawLine(
                                                    color = Color.LightGray,
                                                    start = Offset(0f, 0f),
                                                    end = Offset(0f, size.height),
                                                    strokeWidth = 2.0F
                                                )
                                            }
                                        }
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = BrownDark,
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedLabelColor = Color.White,
                                    trailingIconColor = Color.White,
                                ),
                                shape = RoundedCornerShape(10.dp),
                                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                                visualTransformation = PasswordVisualTransformation()
                            )

                            Text(
                                text = "Ulangi Kata Sandi",
                                style = MaterialTheme.typography.subtitle1.copy(color = BrownDark),
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                value = confirmPassword,
                                onValueChange = { confirmPassword = it },
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                placeholder = { androidx.compose.material.Text(text = "Ulangi Kata Sandi") },
                                leadingIcon = {
                                    Row(
                                        modifier = Modifier.wrapContentWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        content = {
                                            Image(
                                                modifier = Modifier
                                                    .padding(start = 10.dp, end = 10.dp)
                                                    .size(18.dp),
                                                painter = painterResource(id = R.drawable.email),
                                                colorFilter = ColorFilter.tint(BrownDark),
                                                contentDescription = "custom_text_field"
                                            )
                                            Canvas(
                                                modifier = Modifier.height(24.dp)
                                            ) {
                                                // Allows you to draw a line between two points (p1 & p2) on the canvas.
                                                drawLine(
                                                    color = Color.LightGray,
                                                    start = Offset(0f, 0f),
                                                    end = Offset(0f, size.height),
                                                    strokeWidth = 2.0F
                                                )
                                            }
                                        }
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = BrownDark,
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedLabelColor = Color.White,
                                    trailingIconColor = Color.White,
                                ),
                                shape = RoundedCornerShape(10.dp),
                                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                                visualTransformation = PasswordVisualTransformation()
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                text = "Lupa Kata Sandi",
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.subtitle2.copy(color = BrownDark)
                            )
                            Button(
                                onClick = {
                                    if (password == confirmPassword) {
                                        performRegistration(
                                            email,
                                            password,
                                            onRegisterSuccess,
                                            { msg -> errorMessage = msg })
                                    } else {
                                        errorMessage = "Passwords do not match"
                                    }
                                },
                                modifier = Modifier
                                    .padding(top = 30.dp, bottom = 34.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = BrownDark,
                                    contentColor = BrownSemiLight
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                    text = "Daftar",
                                    color = BrownVeryLight,
                                    style = MaterialTheme.typography.button
                                )
                            }

                            val signInText = "Sudah memiliki akun? Login"
                            val signInWord = "Login"
                            val signInAnnotatedString = buildAnnotatedString {
                                append(signInText)

                                // Membuat "Login" klikable
                                val startIndex = signInText.indexOf(signInWord)
                                val endIndex = startIndex + signInWord.length
                                addStyle(
                                    style = SpanStyle(
                                        color = BrownDark,
                                        fontWeight = FontWeight.Medium,
                                        textDecoration = TextDecoration.Underline // Opsional: Tambahkan underline
                                    ),
                                    start = startIndex,
                                    end = endIndex
                                )
                                addStringAnnotation(
                                    tag = "LOGIN", // Tag unik untuk identifikasi klik
                                    annotation = "LoginScreen", // Data yang dikirim saat klik
                                    start = startIndex,
                                    end = endIndex
                                )
                            }

                            ClickableText(
                                text = signInAnnotatedString,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                ),
                                onClick = { offset ->
                                    signInAnnotatedString.getStringAnnotations(
                                        tag = "LOGIN",
                                        start = offset,
                                        end = offset
                                    )
                                        .firstOrNull()?.let { annotation ->
                                            if (annotation.item == "LoginScreen") {
                                                navController.navigate(Destination.Login)
                                            }
                                        }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun performRegistration(
    email: String,
    password: String,
    onRegisterSuccess: () -> Unit,
    onRegisterFailure: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("RegistrasiSuccess", "Registrasi berhasil dengan email: $email")
                onRegisterSuccess()
            } else {
                Log.e("RegistrasiError", "Registrasi gagal: ${task.exception?.localizedMessage}")
            }
        }
}

@Composable
fun HeaderView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 40.dp)
    ) {
        Image(
            modifier = Modifier.wrapContentWidth(),
            bitmap = ImageBitmap.imageResource(id = R.drawable.application_logo),
            contentDescription = "header_view_safedisaster_logo"
        )
        Text(
            text = "SafeDisaster",
            color = Color.White,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 2.sp
            )
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    RegisterScreen( {}, NavController())
//}