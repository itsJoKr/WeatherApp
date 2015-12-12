package com.example.slaven.weatherapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slaven.weatherapp.R;
import com.example.slaven.weatherapp.data.forecast.Forecast;
import com.example.slaven.weatherapp.data.forecast.Weather;
import com.example.slaven.weatherapp.ui.main.adapter.AdapterActivityComm;
import com.example.slaven.weatherapp.ui.main.adapter.AdapterPresenter;
import com.example.slaven.weatherapp.util.Condition;
import com.example.slaven.weatherapp.util.DayNightUtil;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Slaven on 25.7.2015..
 * Package name: com.example.slaven.weatherapp.ui.main
 */
public class ForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int DAY_NAME_HOLDER = 1;
    public static final int WEATHER_HOLDER = 2;

    private Context activityContext;
    private LayoutInflater inflater;

    private AdapterActivityComm activityComm;
    private Forecast forecastData;
    AdapterPresenter adapterPresenter;
    List<AdapterPresenter.TempDataHolder> adapterData;

    public ForecastAdapter(Context activityContext, AdapterActivityComm activityComm) {
        this.activityComm = activityComm;
        this.activityContext = activityContext;
        inflater = LayoutInflater.from(activityContext);
        forecastData = null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;

        if (viewType == DAY_NAME_HOLDER){
            view = inflater.inflate(R.layout.dayname_holder, parent, false);
            viewHolder = new DayHolder(view);
        }
        else if (viewType == WEATHER_HOLDER){
            view = inflater.inflate(R.layout.forecast_holder, parent, false);
            viewHolder = new WeatherHolder(view);
        }
        else  {
            Log.e("USER", "ViewType error: " + viewType);
            return null;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DayHolder){
            ((DayHolder) holder).mDayName.setText(adapterData.get(position).getDayName());
        }
        if (holder instanceof WeatherHolder){
            ((WeatherHolder) holder).mDescription.setText(adapterData.get(position).getWeatherData().getWeather().get(0).getDescription() + "");
            ((WeatherHolder) holder).mTemp.setText((int)(adapterData.get(position).getWeatherData().getMain().getTemp()+0.5) + "ºC");

            Calendar cal = Calendar.getInstance();
            cal.setTime(adapterData.get(position).getWeatherData().getDt_txt());
            ((WeatherHolder) holder).mDate.setText(DayNightUtil.getHourString(cal.get(Calendar.HOUR_OF_DAY)) + ":00");

            Weather weather = adapterData.get(position).getWeatherData().getWeather().get(0);
            int imageId = Condition.getForecastImgId(weather.getId(), DayNightUtil.isDayIcon(weather.getIcon()));
            ((WeatherHolder) holder).mConditionIcon.setImageResource(imageId);
        }
    }

    @Override
    public int getItemCount() {
        if (forecastData == null) {
            return 0;
        }
        else {
            return forecastData.getList().size() + DayNightUtil.checkDaysInForecast(forecastData);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return adapterData.get(position).getTypeHolder();
    }


    // Downloaded forecast data is passed here, and AdapterPresenter will format data for this Adapter
    public void setForecastData(Forecast forecastData) {
        this.forecastData = forecastData;
        adapterPresenter = new AdapterPresenter(forecastData);
        adapterData = adapterPresenter.getAdapterData();
        notifyDataSetChanged();
    }

    class DayHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_day_name)
        TextView mDayName;

        public DayHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class WeatherHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_condition_icon)
        ImageView mConditionIcon;
        @Bind(R.id.txt_main_description)
        TextView mDescription;
        @Bind(R.id.txt_temp)
        TextView mTemp;
        @Bind(R.id.txt_holder_date)
        TextView mDate;

        public WeatherHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activityComm.onHolderClick(adapterData.get(getAdapterPosition()).getWeatherData(), adapterPresenter.getCityName());
                }
            });
        }
    }
}
