import {NextApiResponse, NextApiRequest} from 'next'

export default function handler(
    _req: NextApiRequest) {
    return fetch(`https://what-should-i-eat-375008.ey.r.appspot.com/recipes/random/bytags/Nem%20Hverdagsmad`, {
        method: _req.method,
        redirect: 'manual',
    })
}
