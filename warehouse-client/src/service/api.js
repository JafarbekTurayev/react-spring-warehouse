export const API_PATH = "http://localhost/api/"
export const TOKEN_VALUE = localStorage.getItem("warehouse-token")
export const HEADER = {
    headers: {Authorization: TOKEN_VALUE}
}