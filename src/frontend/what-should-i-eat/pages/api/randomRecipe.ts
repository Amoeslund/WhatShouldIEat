import {type NextRequest} from 'next/server'

export const config = {
    runtime: 'edge',
}

export default async function handler(req: NextRequest) {
    return fetch("http://localhost:8080/recipes/random/bytags/Aftensmad", {
        method: req.method,
        redirect: 'manual',
    })
}
