'use strict';

/* https://github.com/angular/protractor/blob/master/docs/toc.md */

describe('my app', function() {

  browser.get('index.html');

  it('should automatically redirect to /search_view when location hash/fragment is empty', function() {
    expect(browser.getLocationAbsUrl()).toMatch("/search_view");
  });


  describe('search_view', function() {

    beforeEach(function() {
      browser.get('index.html#/search_view');
    });


    it('should render search_view when users navigates to /search_view', function() {
      expect(element.all(by.css('[ng-view] p')).first().getText()).
        toMatch(/partial for view 1/);
    });

  });


  describe('view2', function() {

    beforeEach(function() {
      browser.get('index.html#/view2');
    });


    it('should render view2 when users navigates to /view2', function() {
      expect(element.all(by.css('[ng-view] p')).first().getText()).
        toMatch(/partial for view 2/);
    });

  });
});
