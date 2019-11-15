import { ReactElement } from 'react';

export type Coords = {
  x: number;
  y: number;
};
export type Rect = ClientRect | DOMRect;
export type ElementId = string;
export type ElementType = 'source' | 'target' | 'mapping';
export type GroupId = string;
export interface IFieldsNode {
  id: ElementId;
  element: ReactElement;
}
export interface IFieldsGroup {
  id: GroupId;
  title: ReactElement | string;
  fields: (IFieldsNode | IFieldsGroup)[];
}
export interface IMappings {
  id: string;
  sourceFields: ElementId[];
  targetFields: ElementId[];
}
