<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title>Sarafan</v-toolbar-title>
      <v-spacer></v-spacer>
      <span v-if="profile">{{profile.name}}</span>
      <v-btn v-if="profile" icon href="/logout">
        <logout-icon />
      </v-btn>
    </v-toolbar>
    <v-content>
      <v-container v-if="!profile">
        Необходимо авторизоваться через
        <a href="/login">Google</a>
      </v-container>
      <v-container v-if="profile">
        <messages-list />
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import MessagesList from 'components/messages/MessageList.vue'
import { addHandler } from 'util/ws'
import LogoutIcon from 'vue-material-design-icons/Logout.vue';

export default {
  components: {
    MessagesList,
    LogoutIcon,
  },
  computed: mapState(['profile']),
  methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
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
            console.error(`Looks like the event type if unknown "${data.eventsType}"`)
        }
      } else {
        console.error(`Looks like the object type if unknown "${data.objectType}"`)
      }
    })
  }
}
</script>

<style>
</style>