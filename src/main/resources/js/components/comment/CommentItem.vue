<template>
  <v-list-tile>
    <user-link
      :user="comment.author"
    />
    <v-list-tile-content>
      <v-list-tile-title>{{comment.text}}</v-list-tile-title>
    </v-list-tile-content>
  </v-list-tile>
</template>

<script>
import UserLink from '../UserLink.vue';
export default {
  name: 'CommentItem',
  components: { UserLink },
  props: ['comment']
}
</script>

<style scoped>
</style>