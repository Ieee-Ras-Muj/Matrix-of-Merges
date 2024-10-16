import requests
import sys

# API endpoint and API key (Issue 1: Store the API key securely in environment variables or a config file)
API_KEY = "your_api_key_here"
BASE_URL = "http://api.openweathermap.org/data/2.5/weather"

# Function to get weather data by city name
def get_weather(city):
    try:
        params = {"q": city, "appid": API_KEY, "units": "metric"}
        response = requests.get(BASE_URL, params=params)
        data = response.json()

        if response.status_code == 200:
            main = data['main']
            wind = data['wind']
            weather = data['weather'][0]

            print(f"\nWeather in {city.capitalize()}:")
            print(f"Temperature: {main['temp']}°C")
            print(f"Humidity: {main['humidity']}%")
            print(f"Weather: {weather['description'].capitalize()}")
            print(f"Wind Speed: {wind['speed']} m/s\n")
        else:
            print(f"Error: {data['message']}")  # Issue 2: Handle more specific error codes (404, 500, etc.)
    except Exception as e:
        print(f"Error fetching data: {str(e)}")  # Issue 3: Improve exception handling and logging

# Main function to interact with the user
def main():
    while True:
        print("\nWeather Scraper")
        print("1. Get Weather by City")
        print("2. Exit")

        choice = input("\nEnter your choice (1-2): ")

        if choice == '1':
            city = input("Enter city name: ")
            get_weather(city)
        elif choice == '2':
            print("Exiting the application.")
            sys.exit()
        else:
            print("Invalid choice! Please choose a valid option.")

if __name__ == "__main__":
    main()
