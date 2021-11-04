<template>
  <v-card class="my-2">
    <v-card-text primary-title>
      <i>({{ message.id }})</i> {{ message.text }}
    </v-card-text>
    <v-card-actions>
      <v-btn @click="edit" small flat round>Edit</v-btn>
      <v-btn icon @click="del" small>
        <delete-icon />
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import DeleteIcon from 'vue-material-design-icons/Delete.vue';
import { mapActions } from 'vuex'

export default {
  components: {
    DeleteIcon,
  },
  props: ['message', 'editMessage'],
  methods: {
    ...mapActions(['removeMessageAction']),
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.removeMessageAction(this.message)
    }
  }
}
</script>

<style>
</style>