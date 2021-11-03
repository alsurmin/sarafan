import Vue from 'vue'
import 'api/resource'
import App from 'pages/App.vue'
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
    render: a => a(App)
})