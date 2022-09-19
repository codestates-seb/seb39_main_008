import { setupWorker } from 'msw';
// eslint-disable-next-line import/no-unresolved
import { handlers } from './handlers.js';

export const worker = setupWorker(...handlers);
