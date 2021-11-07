<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title>Sarafan</v-toolbar-title>
      <v-btn flat
             v-if="profile"
             :disabled="$route.path ==='/'"
             @click="showMessages">
        Messages
      </v-btn>
      <v-spacer />
      <v-btn flat
             v-if="profile"
             :disabled="$route.path ==='/profile'"
             @click="showProfile">
        {{profile.name}}
      </v-btn>
      <v-btn v-if="profile" icon href="/logout">
        <logout-icon />
      </v-btn>
    </v-toolbar>
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import { addHandler } from 'util/ws'
import LogoutIcon from 'vue-material-design-icons/Logout.vue';

export default {
  components: {
    LogoutIcon,
  },
  computed: mapState(['profile']),
  methods: {
    ...mapMutations([
        'addMessageMutation',
        'updateMessageMutation',
        'removeMessageMutation',
        'addCommentMutation'
    ]),
    showMessages() {
      this.$router.push('/')
    },
    showProfile() {
      this.$router.push('/profile')
    }
  },
  created() {
    addHandler(data => {
      if (data.objectType === 'MESSAGE') {
        switch (data.eventsType) {
          case 'CREATE':
            this.addMessageMutation(data.body)
            break
          case 'UPDATE':
            this.updateMessageMutation(data.body)
            break
          case 'REMOVE':
            this.removeMessageMutation(data.body)
            break
          default:
            console.error(`Looks like the event type is unknown "${data.eventsType}"`)
        }
      } else if (data.objectType === 'COMMENT') {
        switch (data.eventsType) {
          case 'CREATE':
            this.addCommentMutation(data.body)
            break
          default:
            console.error(`Looks like the event type is unknown "${data.eventsType}"`)
        }
      } else {
        console.error(`Looks like the object type is unknown "${data.objectType}"`)
      }
    })
  },
  beforeMount() {
    if (!this.profile) {
      this.$router.replace('/auth')
    }
  }
}
</script>

<style>
</style>