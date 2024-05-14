package com.example.liteblog.Register.presentation

import android.util.Log
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.R
import com.example.liteblog.utils.Component.MSpacer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.utils.Component.InputPassword
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterScreen(
    navController: NavController = rememberNavController(),
    viewModel: RegisterViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = {
                    viewModel.previousPage()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }},
                title = { Text(text = "Blog Lite")},
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,

                    ),
            )
        }
    ){
        Box {
            Column(modifier = Modifier.padding(it)) {
                if(state.isLoading){
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                when(viewModel.numScreen) {
                    1 -> RS_Screen1(
                        onChangeToLoginScreen = {navController.navigate("login")}
                    )
                    2 -> RS_Screen2(
                        viewModel = viewModel
                    )
                    3 -> RS_Screen3(
                        viewModel = viewModel
                    )
                    4 -> RS_Screen4(
                        viewModel = viewModel
                    )
                    5 -> RS_Screen5(
                        viewModel = viewModel
                    )
                    6 -> RS_Screen6(
                        onChangeToLoginScreen = {navController.navigate("login")}
                    )
                }
            }

            Box (
                modifier = Modifier
                    .padding(it)
                    .absoluteOffset(viewModel.offSetX.dp, 0.dp)
                    .fillMaxHeight()
                    .width(600.dp)
                    .background(Color.White)
            ){
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RS_InputTwoValue(
    input1Value: String = "",
    input2Value: String = "",

    onClickIcon1:() ->Unit = {},
    onClickIcon2:() ->Unit = {},

    label1Text: String = "",
    label2Text: String = "",

    placeholder1: String = "",
    placeholder2: String = "",

    onInput1Change: (String) -> Unit = {},
    onInput2Change: (String) -> Unit = {},
) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            value = input1Value,
            onValueChange = {
                onInput1Change(it)
            },
            label = { Text(text = label1Text)},
            placeholder = { Text(text = placeholder1)},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if(input1Value.length > 0) {
                    IconButton(onClick = {
                        onClickIcon1()
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp)
        )
        MSpacer(0, 12)
        OutlinedTextField(
            value = input2Value,
            onValueChange = {
                onInput2Change(it)
            },
            label = { Text(text = label2Text)},
            placeholder = { Text(text = placeholder2)},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                if(input2Value.length > 0) {
                    IconButton(onClick = {
                        onClickIcon2()
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Composable
fun RS_InputOneValue(
    input1Value: String = "",
    onClickIcon1:() ->Unit = {},
    label1Text: String = "",
    placeholder1: String = "",
    onInput1Change: (String) -> Unit = {},
) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            value = input1Value,
            onValueChange = {
                onInput1Change(it)
            },
            label = { Text(text = label1Text)},
            placeholder = { Text(text = placeholder1)},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if(input1Value.length > 0){
                    IconButton(onClick = {
                        onClickIcon1()
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp)
        )

    }
}

//@Preview(showBackground = true)
@Composable
fun RS_Screen6(
    viewModel: RegisterViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onChangeToLoginScreen: () -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            text = "Đồng ý với điều khoản và chính sách của Blog Lite",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp
        )
        MSpacer(10, 0)
        Text(text = "Bằng cách nhấn vào Tôi đồng ý, bạn đồng ý tạo tài khoản, Chính sách quyền riêng tư, cũng như chấp thuận Điểu khoản của Blog Lite.")
        MSpacer(20, 0)
        Text(text = "Chính sách quyền riêng tư mô tả cách chúng tôi có thể dùng thông tin thu thập được khi bạn tạo tài khoản. Chẳng hạn, chúng tôi sử dụng thông tin này để cung cấp, cá nhân hóa và cải thiện sản phẩm của mình")
        MSpacer(20)
        Button(
            onClick = {
                viewModel.createUser()
                onChangeToLoginScreen()
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tôi đồng ý")
        }
        MSpacer(20)
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Các điều khoản")
        }

    }
}

//@Preview(showBackground = true)
@Composable
fun RS_Screen5(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel()
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            text = "Tạo mật khẩu",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp
        )
        MSpacer(10, 0)
        Text(
            text = "Tạo mật khẩu gồm ít nhất 6 chữ cái hoặc chữ số. Bạn nên chọn mật khẩu thật khó đoán.",
            fontSize = 16.sp,
        )
        MSpacer(20, 0)
        InputPassword(
            input1Value = viewModel.password,
            onInput1Change = {viewModel.password = it},
            onClickIcon1 = {viewModel.isShowPassword = !viewModel.isShowPassword},
            label1Text = "Mật khẩu",
            placeholder1 = "Mật khẩu",
            isShowPassword = viewModel.isShowPassword
        )
        MSpacer(20)
        Button(
            onClick = { viewModel.checkPassword() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tiếp")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun RS_Screen4(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel()
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            text = "Tên đăng nhập của bạn là gì?",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp
        )
        MSpacer(10, 0)
        Text(
            text = "Nhập tên đăng nhập có thể dùng để liên hệ với bạn. Bạn cũng có thể dùng để tìm kiếm những người khác",
            fontSize = 16.sp,
        )
        MSpacer(20, 0)
        RS_InputOneValue(
            input1Value = viewModel.username,
            onClickIcon1 = {viewModel.username = ""},
            onInput1Change = {viewModel.username = it},
            label1Text = "Tên đăng nhập",
            placeholder1 = "Tên đăng nhập",
        )
        MSpacer(20)
        Button(
            onClick = { viewModel.checkUserName() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tiếp")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun RS_Screen3(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel()
) {

    val state = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        yearRange = (1924..2024)
    )
    var isShowDatePicker by remember {
        mutableStateOf(false)
    }
    if (isShowDatePicker) {
        AlertDialog(
            onDismissRequest = { isShowDatePicker = !isShowDatePicker },
        ) {
            Box {
                DatePicker(
                    state = state,
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                )
                Button(
                    onClick = {
                        isShowDatePicker = !isShowDatePicker
                        viewModel.onSelectBirthday(state.selectedDateMillis)
                              },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                ) {
                    Text(text = "Chọn")
                }
            }
        }
        Log.i("huyvu", "${state.selectedDateMillis}")
    }
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            text = "Ngày sinh của bạn là khi nào?",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 30.sp
        )
        MSpacer(10, 0)
        Text(
            text = "Chọn ngày sinh của bạn. Bạn luôn có thể đặt thông tin này ở chế đọ riêng tư vào lúc khác.",
            fontSize = 16.sp,
        )
        MSpacer(20, 0)
        if(viewModel.birthday.year != 0) {
            Text(
                text = "${viewModel.birthday.day} tháng ${viewModel.birthday.month}, ${viewModel.birthday.year}",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        MSpacer(20, 0)
        OutlinedButton(
            onClick = { isShowDatePicker = !isShowDatePicker },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Chọn ngày sinh")
        }
        MSpacer(20)
        Button(
            onClick = { viewModel.checkBirthDay() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tiếp")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun RS_Screen2(
    viewModel: RegisterViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            text = "Bạn tên gì?",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
        )
        MSpacer(10, 0)
        Text(
            text = "Nhập tên bạn sử dụng trong đời thực.",
            fontSize = 16.sp,
            )
        MSpacer(20, 0)
        RS_InputTwoValue(
            input1Value = viewModel.lastname,
            input2Value = viewModel.firstname,

            onClickIcon1 = {viewModel.lastname = ""},
            onClickIcon2 = {viewModel.firstname = ""},

            label1Text = "Họ",
            label2Text = "Tên",

            placeholder1 = "Họ",
            placeholder2 = "Tên",

            onInput1Change = {viewModel.lastname = it},
            onInput2Change = {viewModel.firstname = it},
        )
        MSpacer(20)
        Button(
            onClick = { viewModel.checkFullName() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tiếp")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun RS_Screen1(
    viewModel: RegisterViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onChangeToLoginScreen: ()->Unit = {}
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            text = "Tham gia Blog Lite",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Image(
            painter = painterResource(id = R.drawable.fcfceb56359a94c4cd8b_prev_ui),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
            )
        Text(
            text = "Hãy tạo tài khoản để kết nối với những người bạn và cộng đồng có chung sở thích.",
            fontSize = 16.sp,
        )
        MSpacer(10, 0)
        Button(
            onClick = { viewModel.nextPage() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Bắt đầu")
        }
        MSpacer(5)
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onChangeToLoginScreen()
            }
        ) {
            Text(text = "Tôi có tài khoản rồi")
        }
    }
}