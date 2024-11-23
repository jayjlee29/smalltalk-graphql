import { gql } from "@apollo/client";

// 로그인?
// export const LOGIN_MUTATION = gql`
//   mutation Login($identifier: String!, $userName: String!) {
//     login(identifier: $identifier, userName: $userName) {
//       token
//       userId
//       userName
//     }
//   }
// `;

export const GET_PROFILE = gql`
  query GetProfile {
    getProfile {
      success
      message
      data {
        userId
        userName
      }
    }
  }
`;

export const GET_ARTICLES = gql`
  query GetArticles($input: ArticleQueryRequest!) {
    getArticles(input: $input) {
      code
      message
      data {
        pageNo
        pageSize
        totalCount
        count
        items {
          ... on Article {
            id
            title
            contents
            author
            createdAt
          }
        }
      }
    }
  }
`;

export const GET_BOARDS = gql`
  query GetBoards {
    getBoards {
      code
      message
      data {
        ... on Board {
          id
          name
          description
          enabled
        }
      }
    }
  }
`;

export const CREATE_ARTICLE = gql`
  mutation CreateArticle($input: ArticleCreateRequest!) {
    createArticle(input: $input) {
      code
      message
      data {
        ... on Article {
          id
          title
          contents
          author
        }
      }
    }
  }
`;

export const CREATE_BOARD = gql`
  mutation CreateBoard($input: BoardCreateRequest!) {
    createBoard(input: $input) {
      code
      message
      data {
        ... on Board {
          id
          name
          description
          enabled
        }
      }
    }
  }
`;

export const ENABLE_BOARD = gql`
  mutation EnableBoard($input: BoardEnableRequest!) {
    enableBoard(input: $input) {
      code
      message
      data {
        ... on BooleanWrapper {
          value
        }
      }
    }
  }
`;
