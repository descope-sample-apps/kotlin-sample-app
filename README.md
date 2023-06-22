## Kotlin Sample App
Kotlin Sample app using Descope for authentication. this app includes
- App client
- OTP Email Sign up/Log in
- Flows

## Getting Started
This sample app allows you to get familiar with the Descope Kotlin SDK.

###  Run the app
1. Clone this repo
2. Open the project within Android Studio, or your IDE of choice
3. Within the project settings of the project, change the `<your_project_id>` (MyApplication.kt) to your own 
    - Optionally, if you are using a custom CNAME, you can also add your baseURL
4. If you'd like to run Descope Flows, you'll need to add `<your_flow_url>` (WelcomeScreen.kt), `<your_deep_link_url>` (WelcomeScreen.kt), and `<your_flow_url_host>` (AndroidManifest.xml).
   - You'll also need to uncomment the `FlowDoneActivity` in your `AndroidManifest.xml`
5. Run the simulator within Android Studio - The play button located in the top
