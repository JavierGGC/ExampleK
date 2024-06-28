import com.example.myapplicationexamples3.model.CityRepository
import com.example.myapplicationexamples3.model.GeoApi
import com.example.myapplicationexamples3.model.WeatherRepository
import com.example.myapplicationexamples3.presentation.viewmodel.CityViewModel
import com.example.myapplicationexamples3.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(GeoApi::class.java) }
}

val repositoryModule = module {
    single { CityRepository(get()) }
    single { WeatherRepository(get()) }
}

val viewModelModule = module {
    viewModel { CityViewModel(get()) }
    viewModel { WeatherViewModel(get()) }
}