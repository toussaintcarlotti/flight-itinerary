import com.example.flightitinerary.models.Flight
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("flight/all/{begin}/{end}")
    fun getAllFlights(@Path("begin") begin: Int, @Path("end") end: Int): Call<List<Flight>>
}