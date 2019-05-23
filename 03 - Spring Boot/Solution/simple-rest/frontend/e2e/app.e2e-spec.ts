import { StorefrontPage } from './app.po';

describe('storefront App', function() {
  let page: StorefrontPage;

  beforeEach(() => {
    page = new StorefrontPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
