import Vue from 'vue'
import '@babel/polyfill'
import 'api/resource'
import router from "./router/router";
import App from 'pages/App.vue'
import store from './store/store';
import { connect } from './util/ws'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'vue-material-design-icons/styles.css';

if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})