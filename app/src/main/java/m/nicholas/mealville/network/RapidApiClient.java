package m.nicholas.mealville.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static m.nicholas.mealville.Constants.API_URL;
import static m.nicholas.mealville.Constants.API_HOST;
import static m.nicholas.mealville.Constants.API_KEY;

public class RapidApiClient {
    public static Retrofit retrofit = null;

    public static RapidApi getClient(){
        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("x-rapidapi-host",API_HOST)
                                .addHeader("x-rapidapi-key",API_KEY)
                                .build();
                        return chain.proceed(newRequest);
                    }).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RapidApi.class);
    }


}
