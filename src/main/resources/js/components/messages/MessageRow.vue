<template>
  <v-card class="my-2">
    <v-card-text primary-title>
      <user-link
          :user="message.author"
          size="48"
      />
      <div class="pt-3">
        {{ message.text }}
      </div>
    </v-card-text>
    <media v-if="message.link" :message="message"></media>
    <v-card-actions>
      <v-btn @click="edit" small flat round>Edit</v-btn>
      <v-btn icon @click="del" small>
        <delete-icon />
      </v-btn>
    </v-card-actions>
    <comment-list
        :comments="message.comments"
        :message-id="message.id"
    ></comment-list>
  </v-card>
</template>

<script>
import DeleteIcon from 'vue-material-design-icons/Delete.vue';
import { mapActions } from 'vuex'
import Media from '../media/Media.vue';
import CommentList from '../comment/CommentList.vue';
import UserLink from '../UserLink.vue';

export default {
  components: {
    UserLink,
    CommentList,
    DeleteIcon,
    Media,
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