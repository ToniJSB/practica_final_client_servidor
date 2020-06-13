import Vue from 'vue'
import axios from 'axios'

//  axios.defaults.withCredentials = true;
axios.defaults.headers.common['Authorization'] = 'Bearer '+localStorage.getItem('token');
//axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded';
// Add a request interceptor

export default async ({Vue, router}) => {
  // Add a request interceptor
  axios.interceptors.request.use(function (config) {
   

    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });

// Add a response interceptor
  axios.interceptors.response.use(function (response) {
      // Any status code that lie within the range of 2xx cause this function to trigger
      // Do something with response data
      return response;
    }, function (error) {
      // Any status codes that falls outside the range of 2xx cause this function to trigger
      // Do something with response error
      return Promise.reject(error);
    });

  Vue.prototype.$axios = axios

}